package com.example.cx.parkingmanagementsystem.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.example.cx.parkingmanagementsystem.items.Light;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cx on 2016/12/07.
 */
public class RangeView_Light extends View {
    private List<Light> lights = new ArrayList<>();
    private Light light;
    private float scale = 1;

    public RangeView_Light(Context context) {
        super(context);
    }

    public RangeView_Light(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeView_Light(Context context, AttributeSet attrs, int defStyle) {
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
    }

    /*
    *设置照明灯和摄像头列表
     */
    public void setList(List<Light> lights) {
        this.lights = lights;
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
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
