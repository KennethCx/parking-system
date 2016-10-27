package com.example.cx.parkingmanagementsystem.items;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/25.
 */
public class Parkingspace_V extends item {

    public Parkingspace_V(Context context) {
        super(context, null);
    }

    public Parkingspace_V(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Parkingspace_V(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //停车位宽度和长度
    private int WIDTH = 78;
    private int HEIGHT = 135;

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH(){
        return this.WIDTH;
    }

    public int getHEIGHT(){
        return this.HEIGHT;
    }

    public void setBackground() {

        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.parkingspace_empty);
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
