package com.example.cx.parkingmanagementsystem.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Cx on 2016/11/05.
 */
public class SwitchButton extends Button {

    private boolean Checked = true;

    public SwitchButton(Context context) {
        super(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean getChecked(){
        return Checked;
    }

    public void setChecked(boolean Checked) {
        this.Checked = Checked;
    }

}
