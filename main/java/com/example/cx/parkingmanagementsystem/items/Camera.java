package com.example.cx.parkingmanagementsystem.items;

import android.content.Context;
import android.util.AttributeSet;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/25.
 */
public class Camera extends item {

    private float cameraRange = 400;
    private float cameraAngle = 0;

    private int centreX = 0;
    private int centreY = 0;

    public Camera(Context context) {
        super(context, null);
    }

    public Camera(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Camera(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBackground() {
        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.camera_off);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.camera_on);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.camera_break);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }

    public void setCameraRange(float cameraRange) {
        this.cameraRange = cameraRange;
    }

    public void setCameraAngle(float cameraAngle) {
        this.cameraAngle = cameraAngle;
    }

    public float getCameraRange() {
        return cameraRange;
    }

    public float getCameraAngle() {
        return cameraAngle;
    }

    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }
}
