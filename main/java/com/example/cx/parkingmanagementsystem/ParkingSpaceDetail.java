package com.example.cx.parkingmanagementsystem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cx.parkingmanagementsystem.Internet.getPostUtil;

/**
 * Created by Cx on 2016/12/19.
 */
public class ParkingSpaceDetail extends PopupWindow {
    private final String[] statusContent = {"不可用", "空闲", "占用", "已预约"};

    private int ID;
    String ownerName;
    String plateNumber;


    ParkingSpaceDetail(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public void setDetail(int ID, int Status, String ownerName, String plateNumber) {
        this.ID = ID;
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        if (Status != 2) {
            TextView textView = (TextView) getContentView().findViewById(R.id.text);
            textView.setText("车位号:\n状态:");
            textView = (TextView) getContentView().findViewById(R.id.detail);
            textView.setText(ID + "\n" + statusContent[Status]);
            if (Status == 1) {
                ImageButton imageButton = (ImageButton) getContentView().findViewById(R.id.button_reserve);
                imageButton.setVisibility(View.VISIBLE);
            }
        } else {
            TextView textView = (TextView) getContentView().findViewById(R.id.detail);
            textView.setText(ID + "\n" + statusContent[Status] + "\n" + ownerName + "\n" + plateNumber);
        }
    }
}
