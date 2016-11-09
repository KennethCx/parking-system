package com.example.cx.parkingmanagementsystem;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cx.parkingmanagementsystem.items.Camera;
import com.example.cx.parkingmanagementsystem.items.Hydrant;
import com.example.cx.parkingmanagementsystem.items.Light;
import com.example.cx.parkingmanagementsystem.items.Parkingspace_H;
import com.example.cx.parkingmanagementsystem.items.Parkingspace_V;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cx on 2016/11/01.
 */
public class ParkingLotFragment extends Fragment {
    private List<Parkingspace_H> parkingspaces_h = new ArrayList<>();
    private List<Parkingspace_V> parkingspaces_v = new ArrayList<>();
    private List<Light> lights = new ArrayList<>();
    private List<Camera> cameras = new ArrayList<>();
    private List<Hydrant> hydrants = new ArrayList<>();
    private Parkingspace_H parkingspace_h;
    private Parkingspace_V parkingspace_v;
    private Light light;
    private Camera camera;
    private Hydrant hydrant;

    MyReceiver receiver = new MyReceiver();

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //启动服务
            Intent intent = new Intent(getActivity(), UpdateService.class);
            // 启动IntentService
            getActivity().startService(intent);
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.floor, container, false);
        if (MainActivity.FLOOR == 1)
            rootView = inflater.inflate(R.layout.floor_1, container, false);
        else if (MainActivity.FLOOR == 2)
            rootView = inflater.inflate(R.layout.floor_2, container, false);
        else if (MainActivity.FLOOR == 3)
            rootView = inflater.inflate(R.layout.floor_3, container, false);
        else {
            return rootView;                                                                        //如果是最底层的fragment则直接返回
        }
        //初始化组件ID
        initItemsID(rootView);
        //初始化组件Onclick事件
        setItemsListener();

        handler.postDelayed(runnable, 500);                                                         //每半秒执行一次runnable.

        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.cx.parkingmanagementsystem.UpdateService");
        getActivity().registerReceiver(receiver, filter);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*List<UpdateList> updateLists = new ArrayList<>();
        updateLists.add(new UpdateList("light", 1, -1));
        updateLists.add(new UpdateList("light", 0, 1));
        updateLists.add(new UpdateList("camera", 1, -1));
        updateLists.add(new UpdateList("camera", 0, 1));
        updateLists.add(new UpdateList("hydrant", 0, 1));
        updateLists.add(new UpdateList("hydrant", 1, -1));
        updateLists.add(new UpdateList("parkingspace", 2, -1));
        updateLists.add(new UpdateList("parkingspace", 3, 1));
        updateLists.add(new UpdateList("parkingspace", 1, 0));
        changeItemsStatus(updateLists);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);                                                          //取消定时发送给handle
        getActivity().unregisterReceiver(receiver);                                                 //注销广播接收器
    }

    //初始化控件的ID号
    public void initItemsID(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        RelativeLayout subRelativeLayout;
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            subRelativeLayout = (RelativeLayout) relativeLayout.getChildAt(i);                      //遍历每层停车场中的4种控件

            //初始化停车位
            if (i == 0) {
                for (int j = 0; j < subRelativeLayout.getChildCount(); j++) {
                    View view = subRelativeLayout.getChildAt(j);
                    if (view instanceof Parkingspace_H) {
                        parkingspace_h = (Parkingspace_H) view;
                        parkingspace_h.setID(j + 1);
                        parkingspaces_h.add(parkingspace_h);
                    } else if (view instanceof Parkingspace_V) {
                        parkingspace_v = (Parkingspace_V) view;
                        parkingspace_v.setID(j + 1);
                        parkingspaces_v.add(parkingspace_v);
                    }
                }
            }
            //初始化灯
            else if (i == 1) {
                for (int j = 0; j < subRelativeLayout.getChildCount(); j++) {
                    light = (Light) subRelativeLayout.getChildAt(j);
                    light.setID(j + 1);
                    lights.add(light);
                }
            }
            //初始化摄像头
            else if (i == 2) {
                for (int j = 0; j < subRelativeLayout.getChildCount(); j++) {
                    camera = (Camera) subRelativeLayout.getChildAt(j);
                    camera.setID(j + 1);
                    cameras.add(camera);
                }
            }
            //初始化消防栓
            else if (i == 3) {
                for (int j = 0; j < subRelativeLayout.getChildCount(); j++) {
                    hydrant = (Hydrant) subRelativeLayout.getChildAt(j);
                    hydrant.setID(j + 1);
                    hydrants.add(hydrant);
                }
            }
        }
    }

    //初始化按钮的相应事件
    public void setItemsListener() {
        for (int i = 0; i < parkingspaces_v.size(); i++) {
            parkingspace_v = parkingspaces_v.get(i);
            parkingspace_v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    parkingspace_v = (Parkingspace_V) view;
                    Toast.makeText(getActivity(), "i am parkingspace" + parkingspace_v.getID(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        for (int i = 0; i < parkingspaces_h.size(); i++) {
            parkingspace_h = parkingspaces_h.get(i);
            parkingspace_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    parkingspace_h = (Parkingspace_H) view;
                    Toast.makeText(getActivity(), "i am parkingspace" + parkingspace_h.getID(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        for (int i = 0; i < lights.size(); i++) {
            light = lights.get(i);
            light.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    light = (Light) view;
                    Toast.makeText(getActivity(), "i am light" + light.getID(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        for (int i = 0; i < cameras.size(); i++) {
            camera = cameras.get(i);
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    camera = (Camera) view;
                    Toast.makeText(getActivity(), "i am camera" + camera.getID(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        for (int i = 0; i < hydrants.size(); i++) {
            hydrant = hydrants.get(i);
            hydrant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hydrant = (Hydrant) view;
                    Toast.makeText(getActivity(), "i am hydrant" + hydrant.getID(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //更新控件的实时状态
    public void changeItemsStatus(List<UpdateList> updateList) {
        for (int i = 0; i < updateList.size(); i++) {
            final UpdateList tmp = updateList.get(i);
            new Thread() {
                @Override
                public void run() {
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tmp.getItemKind().equals("light")) {
                                if (!lights.isEmpty()) {
                                    for (int i = 0; i < lights.size(); i++) {
                                        light = lights.get(i);
                                        if (light.getID() == tmp.getID()) {
                                            System.out.println("修改灯值");
                                            light.setSTATUS(tmp.getNewStatus());
                                            break;
                                        }
                                    }
                                }
                            } else if (tmp.getItemKind().equals("camera")) {
                                if (!cameras.isEmpty()) {
                                    for (int i = 0; i < cameras.size(); i++) {
                                        camera = cameras.get(i);
                                        if (camera.getID() == tmp.getID()) {
                                            camera.setSTATUS(tmp.getNewStatus());
                                            break;
                                        }
                                    }
                                }
                            } else if (tmp.getItemKind().equals("hydrant")) {
                                if (!hydrants.isEmpty()) {
                                    for (int i = 0; i < hydrants.size(); i++) {
                                        hydrant = hydrants.get(i);
                                        if (hydrant.getID() == tmp.getID()) {
                                            hydrant.setSTATUS(tmp.getNewStatus());
                                            break;
                                        }
                                    }
                                }
                            } else if (tmp.getItemKind().equals("parkingspace")) {
                                boolean flag = false;
                                for (int i = 0; i < parkingspaces_v.size(); i++) {
                                    if (parkingspaces_v.get(i).getID() == tmp.getID()) {
                                        parkingspace_v = parkingspaces_v.get(i);
                                        parkingspace_v.setSTATUS(tmp.getNewStatus());
                                        flag = true;
                                        break;
                                    }
                                }
                                if (!flag) {
                                    for (int i = 0; i < parkingspaces_h.size(); i++) {
                                        if (parkingspaces_h.get(i).getID() == tmp.getID()) {
                                            parkingspace_h = parkingspaces_h.get(i);
                                            parkingspace_h.setSTATUS(tmp.getNewStatus());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }.start();
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            List<UpdateList> updateLists = (List<UpdateList>) bundle.getSerializable("list");       //接收控件状态更改列表
            changeItemsStatus(updateLists);                                                         //修改控件状态
        }
    }
}

