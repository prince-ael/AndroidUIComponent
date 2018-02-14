package com.demo.nahidul.androiduicomponent.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.nahidul.androiduicomponent.R;
import com.demo.nahidul.androiduicomponent.interfaces.ControlPanelListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexTableFragment extends Fragment implements ControlPanelListener{

    TextView textView;

    public IndexTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_table, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.tv_index_table_msg);
    }

    @Override
    public void onControl() {

        textView.setText("Control Panel Event Detected By Index Table Fragment");
    }
}
