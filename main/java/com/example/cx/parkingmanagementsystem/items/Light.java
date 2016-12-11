package com.example.cx.parkingmanagementsystem.items;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/24.
 */
public class Light extends item {

    private float lightRange = 300;

    private int centreX = 0;
    private int centreY = 0;

    public Light(Context context) {
        super(context, null);
    }

    public Light(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Light(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBackground() {
        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.light_off);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.light_on);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.light_break);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }

    public void setLightRange(float lightRange) {
        this.lightRange = lightRange;
    }

    public float getLightRange() {
        return lightRange;
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
