package com.demo.nahidul.androiduicomponent.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.nahidul.androiduicomponent.interfaces.AdapterListener;
import com.demo.nahidul.androiduicomponent.R;
import com.demo.nahidul.androiduicomponent.models.FakeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nahidul on 28/12/17.
 */

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<FakeModel> models;

    private static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    private int dummyViews = 1;
    private boolean isLoading = false;

    private AdapterListener moreListener;

    public LoadMoreAdapter(Context mContext) {
        this.mContext = mContext;
        models = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView;

        if(viewType == VIEW_TYPE_LOADING){
            itemView = inflater.inflate(R.layout.item_loading, parent, false);
            viewHolder = new LoadingItemViewHolder(itemView);
        }else{
            itemView = inflater.inflate(R.layout.item_data, parent, false);
            viewHolder = new ItemViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder){

            ItemViewHolder ivHolder = (ItemViewHolder) holder;
            FakeModel model = models.get(position);

            ivHolder.itemNo.setText("Item No. "+position);
            ivHolder.lastName.setText(model.getLastName());
            ivHolder.firstName.setText(model.getFirstName());
            ivHolder.dob.setText(model.getDob());
        }else{

            LoadingItemViewHolder liViewHoder = (LoadingItemViewHolder) holder;
            liViewHoder.loadingIndicator.setIndeterminate(true);
        }


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position >= (models.size() - dummyViews) && moreListener != null){
            moreListener.onLoadMore();
            return VIEW_TYPE_LOADING;
        }else{
            return VIEW_TYPE_ITEM;
        }
    }

    public int getDummyViews() {
        return dummyViews;
    }

    public void setDummyViews(int dummyViews) {
        this.dummyViews = dummyViews;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setMoreListener(AdapterListener moreListener) {
        this.moreListener = moreListener;
    }

    public void addData(FakeModel model){
        models.add(model);
    }

    public void removeData(int pos){
        models.remove(pos);
    }

    public void clearAllData(){
        int size = models.size();
        models.clear();
        notifyItemRangeChanged(0,size);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView firstName;
        TextView lastName;
        TextView dob;
        TextView itemNo;

        public ItemViewHolder(View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            dob = itemView.findViewById(R.id.tv_dob);
            itemNo = itemView.findViewById(R.id.tv_itemNo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Adapter Position "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
            remove(getAdapterPosition());
        }
    }

    class LoadingItemViewHolder extends RecyclerView.ViewHolder{

        ProgressBar loadingIndicator;

        public LoadingItemViewHolder(View itemView) {
            super(itemView);
            loadingIndicator = itemView.findViewById(R.id.pb_loading);
        }
    }

    private void remove(int position){

        models.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, models.size());
    }
}
