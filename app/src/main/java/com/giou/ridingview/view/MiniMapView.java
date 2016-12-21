package com.giou.ridingview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.giou.ridingview.objparser.BuilderInterface;
import com.giou.ridingview.objparser.ObjectParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * Author:Giousa
 * Date:2016/12/19
 * Email:65489469@qq.com
 */
public class MiniMapView extends View {

    private static final String TAG = MiniMapView.class.getSimpleName();

    private List<Point> allPoints = new ArrayList<>();

    private Context mContext;
    private ObjectParser mObjectParser;

    public MiniMapView(Context context) {
        super(context);
        init(context);
    }

    public MiniMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //画到背景Bitmap去
        Paint linePen = new Paint();
        linePen.setColor(Color.WHITE);
        linePen.setStrokeWidth(3.5f);

        if(MiniMapView.this.allPoints.size() > 1) {
            Iterator<Point> iter = MiniMapView.this.allPoints.iterator();
            Point first = null;
            Point last = null;
            while(iter.hasNext()) {
                if(first == null) {
                    first = iter.next(); //开始
                }else{
                    if(last != null){
                        first = last;
                    }
                    last = iter.next();//结束

                    canvas.drawLine(first.x, first.y, last.x, last.y, linePen);
                }
            }
        }

        canvas.restore();
    }

    public List<Point> getAllPoints() {
        return allPoints;
    }

    public void loadObjFile(String fileName) {

        mObjectParser = new ObjectParser(mContext, new BuilderInterface() {

            public void setObjFilename(String filename) {

            }

            public void addVertexGeometric(float x, float y, float z) {
                Point point = new Point((int)x, (int)y);
                allPoints.add(point);
            }

            public void addVertexTexture(float u, float v) {

            }

            public void addVertexNormal(float x, float y, float z) {

            }

            public void addPoints(int values[]) {

            }

            public void addLine(int values[]) {

            }

            public void addFace(int vertexIndices[]) {

            }

            public void addObjectName(String name) {

            }

            public void addMapLib(String[] names) {

            }

            public void setCurrentGroupNames(String[] names) {

            }

            public void setCurrentSmoothingGroup(int groupNumber) {

            }

            public void setCurrentUseMap(String name) {

            }

            public void setCurrentUseMaterial(String name) {

            }

            public void newMtl(String name) {

            }

            public void setXYZ(int type, float x, float y, float z) {

            }

            public void setRGB(int type, float r, float g, float b) {

            }

            public void setIllum(int illumModel) {

            }

            public void setD(boolean halo, float factor) {

            }

            public void setNs(float exponent) {

            }

            public void setSharpness(float value) {

            }

            public void setNi(float opticalDensity) {

            }

            public void setMapDecalDispBump(int type, String filename) {

            }

            public void setRefl(int type, String filename) {

            }

            public void doneParsingMaterial() {

            }

            public void doneParsingObj(String filename) {
                Log.d(TAG, "PaintView allPoints is : " + allPoints.size());
            }
        });

        try {
            mObjectParser.parse(fileName);
        } catch (Exception e) {
            Log.d(TAG, "PaintView allPoints is : " + e.getLocalizedMessage());
        }
    }
}