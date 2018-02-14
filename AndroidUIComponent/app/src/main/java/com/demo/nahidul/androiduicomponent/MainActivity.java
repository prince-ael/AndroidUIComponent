package com.demo.nahidul.androiduicomponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.nahidul.androiduicomponent.implementation_from_outsrc.RVActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mComponentRecyclerView;
    private UiComponentAdapter mComponentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mComponentAdapter = new UiComponentAdapter(this);

        mComponentAdapter.addComponentAndHost("Popup Window",
                new Intent(MainActivity.this, PopupActivity.class));

        mComponentAdapter.addComponentAndHost("Load More RecyclerView",
                new Intent(MainActivity.this,RVActivity.class));

        mComponentAdapter.addComponentAndHost("Load More RecyclerView2",
                new Intent(MainActivity.this,RecyclerViewActivity.class));

        mComponentAdapter.addComponentAndHost("ViewPager",
                new Intent(MainActivity.this,ViewPagerActivity.class));

        mComponentAdapter.addComponentAndHost("Sliding Up Panel",
                new Intent(MainActivity.this,SlidingUpPanelActivity.class));

        mComponentRecyclerView = findViewById(R.id.rv_ui_components);

        mComponentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mComponentRecyclerView.setAdapter(mComponentAdapter);
    }
}
