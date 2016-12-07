package com.example.cx.parkingmanagementsystem.items;


import android.content.Context;
import android.util.AttributeSet;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/24.
 */
public class FloorLight extends item {

    public FloorLight(Context context) {
        super(context, null);
    }

    public FloorLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public FloorLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBackground() {
        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.floorlight_off);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.floorlight_on);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.floorlight_break);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }
}
