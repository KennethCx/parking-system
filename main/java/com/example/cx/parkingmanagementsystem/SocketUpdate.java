package com.example.cx.parkingmanagementsystem;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Cx on 2016/11/15.
 */
public class SocketUpdate implements Runnable {
    private Socket socket;
    private Handler handler;
    public Handler revHandler;
    BufferedReader br = null;
    OutputStream os = null;

    SocketUpdate(Handler handler) {
        this.handler = handler;
    }

    public void run() {

        try {
            socket = new Socket(MainActivity.url, 30000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();

            new Thread() {                                                                   //负责轮询的线程
                @Override
                public void run() {
                    while (true) {
                        try {                                                                   //发送刷新指令
                            os.write("refresh".getBytes("utf-8"));
                            String content = null;
                            //接受刷新信息并把结果发送到handler
                            content = br.readLine();
                            Message msg = new Message();
                            msg.what = 0x111;
                            msg.obj = content;
                            handler.sendMessage(msg);
                            sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x222) {                                                 //修改状态
                        //TODO
                    }
                }
            };
            Looper.loop();

        } catch (SocketTimeoutException e1) {
            e1.printStackTrace();
            System.out.println("连接超时");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

