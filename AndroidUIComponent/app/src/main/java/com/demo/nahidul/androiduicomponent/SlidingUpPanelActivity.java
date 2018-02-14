package com.demo.nahidul.androiduicomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SlidingUpPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_up_panel);

        final TextView tv = findViewById(R.id.tv_slider);
        final ImageView preview = findViewById(R.id.preview);
        final ImageView add = findViewById(R.id.add);
        final ImageView msg = findViewById(R.id.msg);

        final TextView clickEventTitle = findViewById(R.id.tv_title_click_event);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingUpPanelLayout supl = findViewById(R.id.supl);
                boolean var = supl.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED;
                if(var){
                    supl.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }else{
                    supl.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                }
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEventTitle.setText("Preview Item Clicked");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEventTitle.setText("Add Item Clicked");
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEventTitle.setText("Message Item Clicked");
            }
        });
    }
}
