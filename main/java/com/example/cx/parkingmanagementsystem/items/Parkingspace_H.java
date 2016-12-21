package com.example.cx.parkingmanagementsystem.items;

import android.content.Context;
import android.util.AttributeSet;

import com.example.cx.parkingmanagementsystem.R;

/**
 * Created by Cx on 2016/10/25.
 */
public class Parkingspace_H extends item {

    public Parkingspace_H(Context context) {
        super(context, null);
    }

    public Parkingspace_H(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground();
    }

    public Parkingspace_H(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //停车位宽度和长度
    private int WIDTH = 135;
    private int HEIGHT = 78;
    private String ownerName = "";
    private String plateNumber = "";
    private String phoneNumber = "";

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public int getHEIGHT() {
        return this.HEIGHT;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBackground() {
        if (STATUS == 0) {
            this.setBackgroundResource(R.drawable.parkingspace_horizontal_empty);
        } else if (STATUS == 1) {
            this.setBackgroundResource(R.drawable.parkingspace_horizontal_occupied);

        } else if (STATUS == 2) {
            this.setBackgroundResource(R.drawable.parkingspace_horizontal_reserved);
        } else if (STATUS == -1) {
            this.setBackgroundResource(R.drawable.parkingspace_horizontal_unusable);
        }
    }

    public void setSTATUS(int status) {
        super.setSTATUS(status);
        setBackground();
    }
}
