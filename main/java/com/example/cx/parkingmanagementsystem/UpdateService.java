package com.example.cx.parkingmanagementsystem;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.example.cx.parkingmanagementsystem.Internet.getPostUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cx on 2016/11/08.
 */
public class UpdateService extends IntentService {

    public UpdateService() {
        super("UpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<UpdateList> updateLists = new ArrayList<>();
        String result = "";

        //获取照明灯状态
        // result = getPostUtil.sendGet(MainActivity.url + "/seconded/android/searchlight.php", PARAMS + i);
        try {
            result = "[{\"ID\":\"1\",\"lightstatus\":\"1\"},{\"ID\":\"2\",\"lightstatus\":\"1\"}]";
            JSONArray jay = new JSONArray(result);
            for (int j = 0; j < jay.length(); j++) {
                JSONObject tmp = (JSONObject) jay.get(j);
                updateLists.add(new UpdateList("light", tmp.getInt("ID"), tmp.getInt("lightstatus")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //获取停车位状态
        //result = getPostUtil.sendGet(MainActivity.url + "/seconded/android/searchcarport.php", PARAMS + i);
        try {
            JSONArray jay = new JSONArray(result);
            for (int j = 0; j < jay.length(); j++) {
                JSONObject tmp = (JSONObject) jay.get(j);
                updateLists.add(new UpdateList("parkingspace", tmp.getInt("ID"), tmp.getInt("carportstatus")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //获取地灯状态
        // result = getPostUtil.sendGet(MainActivity.url + "/seconded/android/searchcarport.php", PARAMS + i);
        try {
            JSONArray jay = new JSONArray(result);
            for (int j = 0; j < jay.length(); j++) {
                JSONObject tmp = (JSONObject) jay.get(j);
                updateLists.add(new UpdateList("floorLight", tmp.getInt("ID"), tmp.getInt("carportstatus")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //获取消防状态
        // result = getPostUtil.sendGet(MainActivity.url + "/seconded/android/searchcarport.php", PARAMS + i);
        try {
            JSONArray jay = new JSONArray(result);
            for (int j = 0; j < jay.length(); j++) {
                JSONObject tmp = (JSONObject) jay.get(j);
                updateLists.add(new UpdateList("hydrant", tmp.getInt("ID"), tmp.getInt("carportstatus")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* int status = (int) (Math.random() * 2);
        System.out.println("random status = " + status);
        updateLists.add(new UpdateList("light", 1, status));*/

        Intent date = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) updateLists);
        date.setAction("com.cx.parkingmanagementsystem.UpdateService");
        date.putExtras(bundle);
        sendBroadcast(date);                                                                        //发送广播
    }
}

