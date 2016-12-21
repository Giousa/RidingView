package com.giou.ridingview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.giou.ridingview.utils.Timer;
import com.giou.ridingview.view.MiniMapView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private String objUrl = "multiPerson/bike.obj";
    private MiniMapView mMiniMapView;
    private ImageView mBikeView;
    private List<Point> mAllPoints;
    private Button mStart;
    private boolean isStart = false;
    private int count = 0;
    private int mSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }


    private void initView() {
        mMiniMapView = (MiniMapView) findViewById(R.id.mini_map_view);
        mBikeView = (ImageView) findViewById(R.id.iv_bike);
        mStart = (Button) findViewById(R.id.btn_start);
        Glide.with(this).load(R.drawable.bike).asGif().into(mBikeView);
        mMiniMapView.loadObjFile(objUrl);
    }

    private void initData() {
        mAllPoints = mMiniMapView.getAllPoints();
        if(mAllPoints != null){
            Log.d(TAG,"allPoints="+ mAllPoints.size());
            mSize = mAllPoints.size()-1;
        }
    }

    private void initListener() {
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isStart){
                    mTimer.stop();
                    isStart = false;
                    mStart.setText("开始");
                    count = 0;
                }else {
                    mTimer.start();
                    isStart = true;
                    mStart.setText("结束");
                }

            }
        });
    }


    private Timer mTimer = new Timer(600, new Timer.OnTimer() {
        @Override
        public void onTime(Timer timer) {
            count++;
            Log.d(TAG,"count="+count);
            if(count > mSize){
                count = 0;
            }
            perfomAnim(count);
        }
    });

    private void perfomAnim(int count){
        if(mAllPoints != null){

            float progressFloat = count / 100.0f;
            int current = (int) (mSize * progressFloat);

            if(current > mSize){
                return;
            }
            if(current == 0){
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.ABSOLUTE, 0,
                        Animation.ABSOLUTE, mAllPoints.get(current).x,
                        Animation.ABSOLUTE, 0,
                        Animation.ABSOLUTE, mAllPoints.get(current).y);
                animation.setFillAfter(true);
                animation.setDuration(1000);
                mBikeView.startAnimation(animation);
            }else{
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.ABSOLUTE, mAllPoints.get(current-1).x,
                        Animation.ABSOLUTE, mAllPoints.get(current).x,
                        Animation.ABSOLUTE, mAllPoints.get(current-1).y,
                        Animation.ABSOLUTE, mAllPoints.get(current).y);
                animation.setFillAfter(true);
                animation.setDuration(1000);
                mBikeView.startAnimation(animation);
            }

        }
    }

}
