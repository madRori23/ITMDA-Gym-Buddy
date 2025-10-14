package com.example.buddylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final Context context;
    private final List<Schedule> scheduleList;

    public ScheduleAdapter(Context context, List<Schedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);

        holder.txtTitle.setText(schedule.getTitle());
        holder.txtDate.setText(schedule.getDate());
        holder.txtTime.setText(schedule.getTime());
        holder.txtLocation.setText(schedule.getLocation());
        holder.txtRequests.setText(schedule.getRequests());

        // no. of requests
        if (!schedule.getRequests().equalsIgnoreCase("No requests yet")) {
            holder.requestContainer.setVisibility(View.VISIBLE);
        } else {
            holder.requestContainer.setVisibility(View.GONE);
        }

        // Cancel schedule button
        holder.btnCancelSchedule.setOnClickListener(v -> {
            scheduleList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, scheduleList.size());
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDate, txtTime, txtLocation, txtRequests;
        Button btnCancelSchedule, btnAccept, btnDecline;
        LinearLayout requestContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtRequests = itemView.findViewById(R.id.txtRequests);
            btnCancelSchedule = itemView.findViewById(R.id.btnCancelSchedule);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnDecline = itemView.findViewById(R.id.btnDecline);
            requestContainer = itemView.findViewById(R.id.requestContainer);
        }
    }
}
