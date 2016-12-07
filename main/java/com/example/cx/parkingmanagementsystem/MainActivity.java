package com.example.cx.parkingmanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    //服务器URL
    public static String url = "http://192.168.191.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton button_light = (ImageButton) findViewById(R.id.button_light);
        ImageButton button_parkingspace = (ImageButton) findViewById(R.id.button_parkingspace);
        ImageButton button_hydrant = (ImageButton) findViewById(R.id.button_hydrant);
        ImageButton button_camera = (ImageButton) findViewById(R.id.button_camera);
        ImageButton button_all = (ImageButton) findViewById(R.id.button_all);

        button_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",0);
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button_parkingspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",1);
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button_hydrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",2);
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",3);
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",4);
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void setting(View view) {
        final EditText URL = new EditText(this);
        URL.setText(url);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置URL").setIcon(android.R.drawable.ic_dialog_info).setView(URL)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                url = URL.getText().toString();
            }
        });
        builder.show();
    }

}