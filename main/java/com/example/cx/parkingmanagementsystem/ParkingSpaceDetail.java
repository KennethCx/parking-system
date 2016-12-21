package com.example.cx.parkingmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
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
    String phoneNumber;
    int cost = 0;


    ParkingSpaceDetail(View contentView, int width, int height, boolean focusable, final Activity activity) {
        super(contentView, width, height, focusable);
        ImageButton imageButton = (ImageButton) getContentView().findViewById(R.id.button_reserve);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID", ID);
                Intent intent = new Intent(activity, booking.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void setDetail(final int ID, int Status, String ownerName, String plateNumber, String phoneNumber) {
        this.ID = ID;
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        this.phoneNumber = phoneNumber;
        if (Status < 2) {
            TextView textView = (TextView) getContentView().findViewById(R.id.text);
            textView.setText("车位号:\n状    态:");
            textView = (TextView) getContentView().findViewById(R.id.detail);
            textView.setText(ID + "\n" + statusContent[Status]);
            if (Status == 1) {
                ImageButton imageButton = (ImageButton) getContentView().findViewById(R.id.button_reserve);
                imageButton.setVisibility(View.VISIBLE);
            }
        } else {
            TextView textView = (TextView) getContentView().findViewById(R.id.detail);
            textView.setText(ID + "\n" + statusContent[Status] + "\n" + ownerName + "\n" + plateNumber + "\n" + phoneNumber);
            if (Status == 2) {
                new Thread() {
                    @Override
                    public void run() {
                        String PARAMS = "carportID=";
                        cost = Integer.parseInt(getPostUtil.sendGet(MainActivity.url + "/seconded/android/searchlight.php", PARAMS + ID));
                    }
                }.start();
                textView.append("\n" + cost);
                textView = (TextView) getContentView().findViewById(R.id.text);
                textView.append("\n实时费用:");
            }
        }
    }
}
