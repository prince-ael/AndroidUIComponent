package com.demo.nahidul.androiduicomponent.implementation_from_outsrc;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.nahidul.androiduicomponent.FakeModel;
import com.demo.nahidul.androiduicomponent.R;

import java.util.List;

/**
 * Created by nahidul on 1/1/18.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Activity activity;
    private List<FakeModel> models;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public RecyclerViewAdapter(RecyclerView recyclerView, List<FakeModel> models, Activity activity) {
        this.activity = activity;
        this.models = models;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_data, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof UserViewHolder) {

            FakeModel model = models.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.firstName.setText(model.getFirstName());
            userViewHolder.lastName.setText(model.getLastName());
            userViewHolder.dob.setText(model.getDob());
            userViewHolder.itemNo.setText("Item No. "+position);

        } else if (holder instanceof LoadingViewHolder) {

            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar =  view.findViewById(R.id.pb_loading);
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView dob;
        TextView itemNo;

        public UserViewHolder(View view) {
            super(view);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            dob = itemView.findViewById(R.id.tv_dob);
            itemNo = itemView.findViewById(R.id.tv_itemNo);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }
}
