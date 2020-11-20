package com.example.examproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkHourAdapter extends RecyclerView.Adapter<WorkHourAdapter.ViewHolder>{

    public List<String> mData;
    public LayoutInflater mInflater;
    public WorkHourAdapter.ItemClickListener mClickListener;

    public List<WorkHour> workHourEntities;

    // data is passed into the constructor
    public WorkHourAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.workHourEntities = new ArrayList<>();
    }

    // inflates the row layout from xml when needed
    @Override
    public WorkHourAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.work_hours, parent, false);
        return new WorkHourAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(WorkHourAdapter.ViewHolder holder, int position) {
        WorkHour workHour = workHourEntities.get(position);
        String workHourComment = workHour.comment;
        holder.myTextView.setText(workHourComment);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return workHourEntities.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvWorkHour);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public WorkHour getItem(int id) {
        return workHourEntities.get(id);
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setWorkHours(List<WorkHour> workHours){
        this.workHourEntities = workHours;
        notifyDataSetChanged();
    }


}
