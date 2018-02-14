package com.demo.nahidul.androiduicomponent.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.nahidul.androiduicomponent.R;
import com.demo.nahidul.androiduicomponent.interfaces.ControlPanelListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements ControlPanelListener{

    TextView textView;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d("www.d.com","called");
        if(context != null){
            Log.d("www.d.com","Context Of "+context.getClass().getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.tv_msg);
    }

    @Override
    public void onControl() {
        textView.setText("MessageFragment Detects Control Panel Event");
    }
}
