package com.example.cx.parkingmanagementsystem.items;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.cx.parkingmanagementsystem.R;

import java.util.HashMap;

/**
 * Created by Cx on 2016/10/24.
 */
public class item extends ImageButton {

    //ID
    public int ID;

    //0关 1开 -1坏
    public int STATUS = 0;

    public item(Context context) {
        super(context, null);
    }

    public item(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public item(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public int getSTATUS(){
        return STATUS;
    }

    public void setSTATUS(int status) {
        this.STATUS = status;
    }
}
