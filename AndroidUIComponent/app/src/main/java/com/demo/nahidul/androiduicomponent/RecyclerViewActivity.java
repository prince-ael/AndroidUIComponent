package com.demo.nahidul.androiduicomponent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.nahidul.androiduicomponent.adapters.LoadMoreAdapter;
import com.demo.nahidul.androiduicomponent.interfaces.AdapterListener;
import com.demo.nahidul.androiduicomponent.models.FakeModel;

/*
* This Activity Holds A RecyclerView That Loads Data When It Scrolls To Its Bottom End
* Primary purpose of this activity is to detect the event when user scrolls to the bottom end of a recycler view.
* So, that we could show some loading indicator to indicate data is loading.
* */

public class RecyclerViewActivity extends AppCompatActivity implements AdapterListener {

    private RecyclerView mLoadMoreRecyclerView;
    private LoadMoreAdapter mLoadMoreAdapter;

    private LinearLayoutManager mLayoutManager;

    private int TOTAL_NUMBER_OF_ITEM = 20;
    private EditText et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mLoadMoreRecyclerView = findViewById(R.id.rv_load_more);

        mLayoutManager = new LinearLayoutManager(this);
        mLoadMoreRecyclerView.setLayoutManager(mLayoutManager);
        et = findViewById(R.id.et_total_count);

        mLoadMoreAdapter = new LoadMoreAdapter(this);
        mLoadMoreAdapter.setMoreListener(this);
        mLoadMoreRecyclerView.setAdapter(mLoadMoreAdapter);

        addDataToAdapter(11);
        mLoadMoreAdapter.notifyDataSetChanged();
    }

    private void detectingScrollEndOfRecyclerView(){

        mLoadMoreRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

//                int visibleItemCount = mLayoutManager.getChildCount();
//                int totalItemCount = mLayoutManager.getItemCount();
//                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
//                  if(pastVisibleItems + visibleItemCount >= totalItemCount){
//
//                      //Reaches to sroll end
//                  }
                /*The above one is a way to detect the bottom end of scrolling of a recycler view*/

                /*The below one is another way to detect the bottom end of scrolling of a recycler view*/
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItem == (mLoadMoreAdapter.getItemCount() - 1)){
                    //Toast.makeText(RecyclerViewActivity.this, "You Are On The Bottom End", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onLoadMore() {

        if(mLoadMoreAdapter.isLoading()){
            return;
        }

//        Log.d("www.d.com", "start size = "+mLoadMoreAdapter.getItemCount());
        if(mLoadMoreAdapter.getItemCount() < TOTAL_NUMBER_OF_ITEM){

            mLoadMoreAdapter.setLoading(true);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("www.d.com","handler started");

                    int dummyPos = mLoadMoreAdapter.getItemCount() - mLoadMoreAdapter.getDummyViews();
                    for(int i = dummyPos; i< mLoadMoreAdapter.getItemCount(); i++){

                        mLoadMoreAdapter.removeData(i);
                    }
                    int itemsToLoad = (TOTAL_NUMBER_OF_ITEM-mLoadMoreAdapter.getItemCount() >= 10) ? 10 :(TOTAL_NUMBER_OF_ITEM-mLoadMoreAdapter.getItemCount()) % 10;
                    Log.d("www.d.com","total "+TOTAL_NUMBER_OF_ITEM);
                    Log.d("www.d.com","adpterSize "+mLoadMoreAdapter.getItemCount());
                    Log.d("www.d.com","Remainder "+(TOTAL_NUMBER_OF_ITEM-mLoadMoreAdapter.getItemCount()) % 10);
                    addDataToAdapter(itemsToLoad);
                    if(mLoadMoreAdapter.getItemCount() < TOTAL_NUMBER_OF_ITEM){
                        for(int i = 0; i < mLoadMoreAdapter.getDummyViews(); i++){
                            mLoadMoreAdapter.addData(new FakeModel());
                        }
                    }
                    mLoadMoreAdapter.notifyDataSetChanged();
                    mLoadMoreAdapter.setLoading(false);
                }
            },1000);
        }else{
            Toast.makeText(this, "No More Item To Load",Toast.LENGTH_SHORT).show();
            mLoadMoreAdapter.setMoreListener(null);
            mLoadMoreAdapter.setDummyViews(0);
        }
    }

    private void addDataToAdapter(int itemsToLoad){

        FakeModel model;
        for(int i = 0; i < itemsToLoad; i++){

            model = new FakeModel();
            model.setLastName("Carter");
            model.setFirstName("John");
            model.setDob("02/12/1862");
            mLoadMoreAdapter.addData(model);
        }
    }

    public void onClickGo(View view) {

        String total = et.getText().toString();

        try{
            TOTAL_NUMBER_OF_ITEM = Integer.parseInt(total);
            et.setText("Total Count : "+TOTAL_NUMBER_OF_ITEM);
            mLoadMoreAdapter.clearAllData();
            addDataToAdapter(5);
            mLoadMoreAdapter.notifyDataSetChanged();

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

        }catch(NumberFormatException nfe){
            et.setText("Input Rejected");
        }
    }
}
