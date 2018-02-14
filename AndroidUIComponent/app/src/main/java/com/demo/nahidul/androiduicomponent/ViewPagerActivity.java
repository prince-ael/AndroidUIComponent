package com.demo.nahidul.androiduicomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.nahidul.androiduicomponent.fragments.SampleFragment;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_holder, new SampleFragment())
                .commit();
    }
}
