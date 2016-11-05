package com.example.cx.parkingmanagementsystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cx on 2016/11/01.
 */
public class ParkingLotFragment extends Fragment {
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

        return rootView;
    }

}
