package com.example.cx.parkingmanagementsystem;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

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
        int ID, NewStatus = 1;
        String Kind = "light";
        //ID = 1;
        NewStatus = (int) (Math.random() + 0.5);
        // System.out.println(NewStatus);
        List<UpdateList> updateLists = new ArrayList<>();
        String result = "";
        for (int i = 1; i <= 2; i++) {
            String PARAMS = "lightID=";
            // result = getPostUtil.sendGet(MainActivity.url, PARAMS + i);
            try {
                result = "[{\"ID\":1,\"status\":" + NewStatus + "}]";
                JSONArray jay = new JSONArray(result);
                for (int j = 0; j < jay.length(); j++) {
                    JSONObject tmp = (JSONObject) jay.get(j);
                    updateLists.add(new UpdateList("light", i, tmp.getInt("status")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent date = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) updateLists);
        date.setAction("com.cx.parkingmanagementsystem.UpdateService");
        date.putExtras(bundle);
        sendBroadcast(date);                                                                        //发送广播
    }
}

