package com.example.cx.parkingmanagementsystem.items;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/24.
 */
public class Light extends item {

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
            this.setBackgroundResource(R.drawable.lightbutton);
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
}
