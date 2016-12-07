package com.example.cx.parkingmanagementsystem;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cx.parkingmanagementsystem.Internet.getPostUtil;

/**
 * Created by Cx on 2016/11/10.
 */
public class ItemDetail extends PopupWindow {
    public static final int PARKINGSPACE = 0;
    public static final int LIGHT = 1;
    public static final int CAMERA = 2;
    public static final int HYDRANT = 3;
    public static final int FLOORLIGHT = 4;
    private final String[][] statusContent = {
            {"不可用", "空闲", "占用"},
            {"损坏", "关闭", "开启"},
            {"损坏", "关闭", "开启"},
            {"损坏", "正常", "开启"},
            {"损坏", "关闭", "开启"}};

    private int type;
    private int ID;

    ItemDetail(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        final Switch switch1 = (Switch) getContentView().findViewById(R.id.Switch);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked()) {                                                          //开灯
                    TextView detailSTATUE = (TextView) getContentView().findViewById(R.id.STATUS);
                    detailSTATUE.setText(statusContent[type][2]);
                    new Thread() {
                        @Override
                        public void run() {
                            if (type == LIGHT) {
                                System.out.println("!!!@@!");
                                String URL = MainActivity.url + "/seconded/android/lightturnon.php";
                                getPostUtil.sendGet(URL, "lightID=" + ID);
                            }
                        }
                    }.start();

                } else {                                                                            //关灯
                    TextView detailSTATUE = (TextView) getContentView().findViewById(R.id.STATUS);
                    detailSTATUE.setText(statusContent[type][1]);
                    new Thread() {
                        @Override
                        public void run() {
                            if (type == LIGHT) {
                                String URL = MainActivity.url + "/seconded/android/lightturnoff.php";
                                getPostUtil.sendGet(URL, "lightID=" + ID);
                            }
                        }
                    }.start();
                }
            }
        });
    }

    public void setDetail(int type, int ID, int Status) {
        this.ID = ID;
        this.type = type;
        boolean on = Status == 2 ? true : false;                                                                         //设置开关时候为开
        boolean flag = Status == 0 ? false : true;                                                                        //设置开关时候可用
        if (type == PARKINGSPACE || type == HYDRANT)
            flag = on = false;

        TextView detailID = (TextView) getContentView().findViewById(R.id.ID);
        detailID.setText(ID + "");
        TextView detailSTATUE = (TextView) getContentView().findViewById(R.id.STATUS);
        detailSTATUE.setText(statusContent[type][Status]);
        Switch switch1 = (Switch) getContentView().findViewById(R.id.Switch);
        switch1.setChecked(on);
        switch1.setEnabled(flag);
    }
}
