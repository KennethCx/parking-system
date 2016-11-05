package com.example.cx.parkingmanagementsystem;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.cx.parkingmanagementsystem.widget.HVScrollView;
import com.example.cx.parkingmanagementsystem.widget.SwitchButton;


public class MainActivity extends AppCompatActivity {
    //当前所在的停车场层数
    public static int FLOOR = 0;
    private Spinner spinner;
    private HVScrollView hvScrollView;
    private ParkingLotFragment parkingLotFragment;
    FloatingActionButton fab;
    private SwitchButton hydrantButton;
    private SwitchButton lightButton;
    private SwitchButton cameraButton;
    private boolean showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FLOOR = 1;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.floorView, new ParkingLotFragment());
        transaction.commit();

        hvScrollView = (HVScrollView) findViewById(R.id.HVScrollView);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(FLOOR - 1, true);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FLOOR = i + 1;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.floorView, new ParkingLotFragment());
                transaction.commit();
                hvScrollView.init();
                lightButton.setBackgroundResource(R.drawable.button_light_on);
                lightButton.setChecked(true);
                cameraButton.setBackgroundResource(R.drawable.button_camera_on);
                cameraButton.setChecked(true);
                hydrantButton.setBackgroundResource(R.drawable.button_hydrant_on);
                hydrantButton.setChecked(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        hydrantButton = (SwitchButton) findViewById(R.id.button_hydrant);
        cameraButton = (SwitchButton) findViewById(R.id.button_camera);
        lightButton = (SwitchButton) findViewById(R.id.button_light);
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
                    lightButton.setBackgroundResource(R.drawable.button_light);
                    lightButton.setChecked(false);
                } else {
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lightView);
                    relativeLayout.setVisibility(View.VISIBLE);
                    lightButton.setBackgroundResource(R.drawable.button_light_on);
                    lightButton.setChecked(true);
                }
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cameraButton.getChecked()) {
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.cameraView);
                    relativeLayout.setVisibility(View.GONE);
                    cameraButton.setBackgroundResource(R.drawable.button_camera);
                    cameraButton.setChecked(false);
                } else {
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.cameraView);
                    relativeLayout.setVisibility(View.VISIBLE);
                    cameraButton.setBackgroundResource(R.drawable.button_camera_on);
                    cameraButton.setChecked(true);
                }
            }
        });
        hydrantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hydrantButton.getChecked()) {
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hydrantView);
                    relativeLayout.setVisibility(View.GONE);
                    hydrantButton.setBackgroundResource(R.drawable.button_hydrant);
                    hydrantButton.setChecked(false);

                } else {
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hydrantView);
                    relativeLayout.setVisibility(View.VISIBLE);
                    hydrantButton.setBackgroundResource(R.drawable.button_hydrant_on);
                    hydrantButton.setChecked(true);
                }
            }
        });
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
