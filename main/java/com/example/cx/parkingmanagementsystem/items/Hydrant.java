package com.example.cx.parkingmanagementsystem.items;

import android.content.Context;
import android.util.AttributeSet;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/25.
 */
public class Hydrant extends item {

    public Hydrant(Context context) {
        super(context, null);
    }

    public Hydrant(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Hydrant(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBackground() {
        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.hydrant_normal);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.hydrant_opened);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.hydrant_break);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }
}
