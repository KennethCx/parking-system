package com.example.cx.parkingmanagementsystem;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import com.example.cx.parkingmanagementsystem.widget.HVScrollView;
import com.example.cx.parkingmanagementsystem.widget.RangeView_Camera;
import com.example.cx.parkingmanagementsystem.widget.RangeView_Light;
import com.example.cx.parkingmanagementsystem.widget.SwitchButton;


public class DisplayActivity extends AppCompatActivity {

    //当前所在的停车场层数
    public static int FLOOR = 0;
    private int type = 1;
    private Spinner spinner;
    private HVScrollView hvScrollView;
    FloatingActionButton fab;
    private SwitchButton hydrantButton;
    private SwitchButton lightButton;
    private SwitchButton cameraButton;
    private SwitchButton lightRangeButton;
    private SwitchButton cameraRangeButton;
    private boolean showButton;
    public static int[][] layout = {{R.layout.floor_1_light_layout, R.layout.floor_2_light_layout, R.layout.floor_3_light_layout},
            {R.layout.floor_1_parkingspace_layout, R.layout.floor_2_parkingspace_layout, R.layout.floor_3_parkingspace_layout},
            {R.layout.floor_1_hydrant_layout, R.layout.floor_2_hydrant_layout, R.layout.floor_3_hydrant_layout},
            {R.layout.floor_1_camera_layout, R.layout.floor_2_camera_layout, R.layout.floor_3_camera_layout},
            {R.layout.floor_1, R.layout.floor_2, R.layout.floor_3}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        type = bundle.getInt("type");
        //初始化停车场使其位于第一层
        setFLOOR(1);

        hvScrollView = (HVScrollView) findViewById(R.id.HVScrollView);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(FLOOR - 1, true);
        hvScrollView.init();
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setFLOOR(i + 1);
                hvScrollView.init();
                if (type == 4) {
                    lightButton.setBackgroundResource(R.drawable.fab_button_light_on);
                    lightButton.setChecked(true);
                    cameraButton.setBackgroundResource(R.drawable.fab_button_camera_on);
                    cameraButton.setChecked(true);
                    hydrantButton.setBackgroundResource(R.drawable.fab_button_hydrant_on);
                    hydrantButton.setChecked(true);
                    lightRangeButton.setBackgroundResource(R.drawable.light_range_on);
                    lightRangeButton.setChecked(true);
                    cameraRangeButton.setBackgroundResource(R.drawable.camera_range_on);
                    cameraRangeButton.setChecked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //右下的悬浮按钮

        fab = (FloatingActionButton) findViewById(R.id.fab);
        hydrantButton = (SwitchButton) findViewById(R.id.fab_button_hydrant);
        cameraButton = (SwitchButton) findViewById(R.id.fab_button_camera);
        lightButton = (SwitchButton) findViewById(R.id.fab_button_light);
        lightRangeButton = (SwitchButton) findViewById(R.id.button_lightRange);
        cameraRangeButton = (SwitchButton) findViewById(R.id.button_cameraRange);
        if (type == 4) {
            fab.setVisibility(View.VISIBLE);
            showButton = false;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showButton) {
                        Animation hydrantAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_hydrant_disapper);
                        Animation cameraAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_camera_disapper);
                        Animation lightAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_light_disapper);
                        hydrantButton.setVisibility(View.GONE);
                        hydrantButton.startAnimation(hydrantAnim);
                        cameraButton.setVisibility(View.GONE);
                        cameraButton.startAnimation(cameraAnim);
                        lightButton.setVisibility(View.GONE);
                        lightButton.startAnimation(lightAnim);
                        fab.setImageResource(R.drawable.fab_show);
                        showButton = false;
                    } else {
                        Animation hydrantAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_hydrant_show);
                        Animation cameraAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_camera_show);
                        Animation lightAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_light_show);
                        hydrantButton.setVisibility(View.VISIBLE);
                        hydrantButton.startAnimation(hydrantAnim);
                        cameraButton.setVisibility(View.VISIBLE);
                        cameraButton.startAnimation(cameraAnim);
                        lightButton.setVisibility(View.VISIBLE);
                        lightButton.startAnimation(lightAnim);
                        fab.setImageResource(R.drawable.fab_close);
                        showButton = true;
                    }

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                }
            });

            lightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lightButton.getChecked()) {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lightView);
                        relativeLayout.setVisibility(View.GONE);
                        RangeView_Light rangeView_light = (RangeView_Light) findViewById(R.id.rangeView_Light);
                        rangeView_light.setVisibility(View.GONE);
                        lightButton.setBackgroundResource(R.drawable.fab_button_light);
                        lightButton.setChecked(false);
                        lightRangeButton.setBackgroundResource(R.drawable.light_range_off);
                        lightRangeButton.setChecked(false);
                    } else {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lightView);
                        relativeLayout.setVisibility(View.VISIBLE);
                        RangeView_Light rangeView_light = (RangeView_Light) findViewById(R.id.rangeView_Light);
                        rangeView_light.setVisibility(View.VISIBLE);
                        lightButton.setBackgroundResource(R.drawable.fab_button_light_on);
                        lightButton.setChecked(true);
                        lightRangeButton.setBackgroundResource(R.drawable.light_range_on);
                        lightRangeButton.setChecked(true);
                    }
                }
            });
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cameraButton.getChecked()) {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.cameraView);
                        relativeLayout.setVisibility(View.GONE);
                        RangeView_Camera rangeView_camera = (RangeView_Camera) findViewById(R.id.rangeView_Camera);
                        rangeView_camera.setVisibility(View.GONE);
                        cameraButton.setBackgroundResource(R.drawable.fab_button_camera);
                        cameraButton.setChecked(false);
                        cameraRangeButton.setBackgroundResource(R.drawable.camera_range_off);
                        cameraRangeButton.setChecked(false);
                    } else {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.cameraView);
                        relativeLayout.setVisibility(View.VISIBLE);
                        RangeView_Camera rangeView_camera = (RangeView_Camera) findViewById(R.id.rangeView_Camera);
                        rangeView_camera.setVisibility(View.VISIBLE);
                        cameraButton.setBackgroundResource(R.drawable.fab_button_camera_on);
                        cameraButton.setChecked(true);
                        cameraRangeButton.setBackgroundResource(R.drawable.camera_range_on);
                        cameraRangeButton.setChecked(true);
                    }
                }
            });
            hydrantButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hydrantButton.getChecked()) {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hydrantView);
                        relativeLayout.setVisibility(View.GONE);
                        hydrantButton.setBackgroundResource(R.drawable.fab_button_hydrant);
                        hydrantButton.setChecked(false);

                    } else {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hydrantView);
                        relativeLayout.setVisibility(View.VISIBLE);
                        hydrantButton.setBackgroundResource(R.drawable.fab_button_hydrant_on);
                        hydrantButton.setChecked(true);
                    }
                }
            });
        } else {
            fab.setVisibility(View.GONE);
            hydrantButton.setVisibility(View.GONE);
            cameraButton.setVisibility(View.GONE);
            lightButton.setVisibility(View.GONE);
        }

        //照明与摄像头范围开关按钮

        if (type == 0 || type == 4) {
            lightRangeButton.setVisibility(View.VISIBLE);
            lightRangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lightRangeButton.getChecked()) {
                        RangeView_Light rangeView_light = (RangeView_Light) findViewById(R.id.rangeView_Light);
                        rangeView_light.setVisibility(View.GONE);
                        lightRangeButton.setBackgroundResource(R.drawable.light_range_off);
                        lightRangeButton.setChecked(false);
                    } else {
                        RangeView_Light rangeView_light = (RangeView_Light) findViewById(R.id.rangeView_Light);
                        rangeView_light.setVisibility(View.VISIBLE);
                        lightRangeButton.setBackgroundResource(R.drawable.light_range_on);
                        lightRangeButton.setChecked(true);
                    }
                }
            });
        } else {
            lightRangeButton.setVisibility(View.GONE);
        }

        if (type == 3 || type == 4) {
            cameraRangeButton.setVisibility(View.VISIBLE);
            cameraRangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cameraRangeButton.getChecked()) {
                        RangeView_Camera rangeView_camera = (RangeView_Camera) findViewById(R.id.rangeView_Camera);
                        rangeView_camera.setVisibility(View.GONE);
                        cameraRangeButton.setBackgroundResource(R.drawable.camera_range_off);
                        cameraRangeButton.setChecked(false);
                    } else {
                        RangeView_Camera rangeView_camera = (RangeView_Camera) findViewById(R.id.rangeView_Camera);
                        rangeView_camera.setVisibility(View.VISIBLE);
                        cameraRangeButton.setBackgroundResource(R.drawable.camera_range_on);
                        cameraRangeButton.setChecked(true);
                    }
                }
            });
        } else {
            cameraRangeButton.setVisibility(View.GONE);
        }
    }

    //设置停车场层数为第i层 并替换Fragment
    public void setFLOOR(int i) {

        FLOOR = i;
        Bundle bundle = new Bundle();
        bundle.putInt("layout", layout[type][i - 1]);
        bundle.putInt("type", type);
        ParkingLotFragment parkingLotFragment = new ParkingLotFragment();
        parkingLotFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.floorView, parkingLotFragment);
        transaction.commit();
    }

    public void back(View view) {
        Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
        startActivity(intent);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}