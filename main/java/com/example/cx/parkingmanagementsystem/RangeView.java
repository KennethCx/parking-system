package com.example.cx.parkingmanagementsystem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.cx.parkingmanagementsystem.items.Camera;
import com.example.cx.parkingmanagementsystem.items.Light;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cx on 2016/12/07.
 */
public class RangeView extends View {
    private List<Camera> cameras = new ArrayList<>();
    private List<Light> lights = new ArrayList<>();
    private Camera camera;
    private Light light;
    private float scale = 1;

    public RangeView(Context context) {
        super(context);
    }

    public RangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        System.out.println("ondraw");
        initList();
        if (!lights.isEmpty()) {
            for (int i = 0; i < lights.size(); i++) {
                light = lights.get(i);
                if (light.getSTATUS() == 1) {
                    Paint paint = new Paint();
                    paint.setColor(Color.argb(50, 248, 242, 37));
                    canvas.drawCircle(light.getCentreX(), light.getCentreY(), light.getLightRange() * scale, paint);
                }
            }
        }

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
    *设置照明灯和摄像头列表
     */
    public void setList(List<Light> lights, List<Camera> cameras) {
        this.lights = lights;
        this.cameras = cameras;
    }

    /*
    *初始化列表中灯或摄像头的中心位置
     */
    public void initList() {
        for (int i = 0; i < lights.size(); i++) {
            light = lights.get(i);
            light.setCentreX((int) (light.getWidth() / 2.0 + light.getX()));
            light.setCentreY((int) (light.getHeight() / 2.0 + light.getY()));
        }
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
