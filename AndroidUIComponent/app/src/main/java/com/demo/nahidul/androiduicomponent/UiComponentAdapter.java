package com.demo.nahidul.androiduicomponent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nahidul on 26/12/17.
 */

public class UiComponentAdapter extends RecyclerView.Adapter<UiComponentAdapter.ComponentViewHolder>{

    private List<String> mComponentNames;
    private List<Intent> mIntents;
    private Context mContext;

    public UiComponentAdapter(Context mContext) {
        this.mContext = mContext;
        mComponentNames = new ArrayList<>();
        mIntents = new ArrayList<>();
    }

    public void addComponentAndHost(String componentName, Intent hostActivity){
        mComponentNames.add(componentName);
        mIntents.add(hostActivity);
    }

    @Override
    public ComponentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ComponentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_ui_component, parent, false));
    }

    @Override
    public void onBindViewHolder(ComponentViewHolder holder, int position) {

        holder.componentName.setText(mComponentNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mComponentNames.size();
    }

     class ComponentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView componentName;

        public ComponentViewHolder(View itemView) {
            super(itemView);
            componentName = itemView.findViewById(R.id.tv_component_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mContext.startActivity(mIntents.get(getAdapterPosition()));
        }
    }
}
