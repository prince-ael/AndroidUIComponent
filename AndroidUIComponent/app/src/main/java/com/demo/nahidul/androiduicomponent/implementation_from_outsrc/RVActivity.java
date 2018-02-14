package com.demo.nahidul.androiduicomponent.implementation_from_outsrc;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.demo.nahidul.androiduicomponent.models.FakeModel;
import com.demo.nahidul.androiduicomponent.R;

import java.util.ArrayList;
import java.util.List;

public class RVActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<FakeModel> models;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        FakeModel model;
        models = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            model = new FakeModel();
            model.setDob("01/01/2015");
            model.setFirstName("Martin");
            model.setLastName("Luther King");
            models.add(model);
        }

        mRecyclerView = findViewById(R.id.rv_load_more2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewAdapter(mRecyclerView, models, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if (models.size() <= 20) {
                    models.add(null);
                    mAdapter.notifyItemInserted(models.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            models.remove(models.size() - 1);
                            mAdapter.notifyItemRemoved(models.size());

                            //Generating more data
                            int index = models.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                FakeModel model = new FakeModel();
                                model.setDob("01/01/2015");
                                model.setFirstName("Martin");
                                model.setLastName("Luther King");
                                models.add(model);
                            }
                            mAdapter.notifyDataSetChanged();
                            mAdapter.setLoaded();
                        }
                    }, 2000);
                } else {
                    Toast.makeText(RVActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
