package com.example.buddylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewScheduleAdapter extends RecyclerView.Adapter<ViewScheduleAdapter.ViewHolder> {

    private final Context context;
    private final List<PassHolderSchedule> schedules;
    private final OnItemActionListener listener;

    public interface OnItemActionListener {
        void onUpdate(PassHolderSchedule schedule);
        void onDelete(PassHolderSchedule schedule);
    }

    // Constructor
    public ViewScheduleAdapter(Context context, List<PassHolderSchedule> schedules, OnItemActionListener listener) {
        this.context = context;
        this.schedules = schedules;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_schedule_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PassHolderSchedule schedule = schedules.get(position);

        // Bind data to TextViews
        holder.txtTitle.setText(schedule.getTitle());
        holder.txtDate.setText(schedule.getDate());
        holder.txtTime.setText(schedule.getTime());

        holder.btnUpdate.setOnClickListener(v -> listener.onUpdate(schedule));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(schedule));
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDate, txtTime;
        Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtScheduleTitle);
            txtDate = itemView.findViewById(R.id.txtScheduleDate);
            txtTime = itemView.findViewById(R.id.txtScheduleTime);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
