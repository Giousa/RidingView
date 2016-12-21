package com.giou.ridingview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private Timer mTimer = new Timer(600, new Timer.OnTimer() {
        @Override
        public void onTime(Timer timer) {
            count++;
            Log.d(TAG,"count="+count);

        }
    });


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


}
