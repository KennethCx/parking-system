package com.example.cx.parkingmanagementsystem;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cx on 2016/12/20.
 */
public class booking extends Activity {

    int ID;

    Button submit;
    Button cancel;
    EditText ownerName;
    EditText plateNumber;
    EditText phoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getInt("ID");
        TextView textView = (TextView) findViewById(R.id.ID);
        textView.setText("   " + ID);
        ownerName = (EditText) findViewById(R.id.ownerName);
        plateNumber = (EditText) findViewById(R.id.plateNumber);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ownerName.length() < 1) {
                    Toast.makeText(getApplication(), "车主不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (plateNumber.length() < 1) {
                    Toast.makeText(getApplication(), "车牌不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
