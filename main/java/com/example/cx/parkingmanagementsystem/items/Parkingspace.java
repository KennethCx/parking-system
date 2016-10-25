package com.example.cx.parkingmanagementsystem.items;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/25.
 */
public class Parkingspace extends item {


    public Parkingspace(Context context) {
        super(context, null);
    }

    public Parkingspace(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Parkingspace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBackground() {
        if (STATUS == 0) {
            //this.setBackgroundResource(R.drawable.parkingspace_empty);
            this.setBackgroundColor(Color.GREEN);
            this.setAlpha(100);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.parkingspace_occupied);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.parkingspace_unusable);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }
}
