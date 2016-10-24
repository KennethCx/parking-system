package com.example.cx.parkingmanagementsystem.electronics;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/24.
 */
public class Light extends ImageButton {

    //0关 1开 -1坏
    int LIGHT_STATUS = 0;

    public Light(Context context) {
        super(context, null);
        System.out.println(1);
    }

    public Light(Context context, AttributeSet attrs) {
        super(context,attrs);
        System.out.println("2");
    }

    public Light(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        System.out.println("3");
        if(LIGHT_STATUS == 0)
            this.setBackgroundResource(R.drawable.light_off);
        else if (LIGHT_STATUS == 1) {
            this.setBackgroundResource(R.drawable.light_on);
        } else if (LIGHT_STATUS == -1) {
            this.setBackgroundResource(R.drawable.light_break);
        }
    }



}
