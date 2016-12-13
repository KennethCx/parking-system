package com.example.cx.parkingmanagementsystem.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.example.cx.parkingmanagementsystem.items.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cx on 2016/12/07.
 */
public class RangeView_Camera extends View {
    private List<Camera> cameras = new ArrayList<>();
    private Camera camera;
    private float scale = 1;

    public RangeView_Camera(Context context) {
        super(context);
    }

    public RangeView_Camera(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeView_Camera(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        initList();
        if (!cameras.isEmpty()) {
            for (int i = 0; i < cameras.size(); i++) {
                camera = cameras.get(i);
                if (camera.getSTATUS() == 1) {
                    canvas.translate(camera.getCentreX(), camera.getCentreY());
                    Paint paint = new Paint();
                    paint.setColor(Color.argb(50, 0, 100, 0));
                    float r = camera.getCameraRange() * scale;
                    RectF rect = new RectF(-r, -r, r, r);
                    canvas.drawArc(rect, camera.getCameraAngle(), camera.getCameraAngle() + 50, true, paint);
                    canvas.translate(-camera.getCentreX(), -camera.getCentreY());
                }
            }
        }
    }

    /*
    *设置摄像头列表
     */
    public void setList(List<Camera> cameras) {
        this.cameras = cameras;
    }

    /*
    *初始化列表中摄像头的中心位置
     */
    public void initList() {
        for (int i = 0; i < cameras.size(); i++) {
            camera = cameras.get(i);
            camera.setCentreX((int) (camera.getWidth() / 2.0 + camera.getX()));
            camera.setCentreY((int) (camera.getHeight() / 2.0 + camera.getY()));
        }
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
