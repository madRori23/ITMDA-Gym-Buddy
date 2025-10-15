package com.example.buddylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final Context context;
    private final List<PassHolderSchedule> scheduleList;

    public ScheduleAdapter(Context context, List<PassHolderSchedule> scheduleList) {
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
        PassHolderSchedule schedule = scheduleList.get(position);

        holder.txtTitle.setText(schedule.getTitle());
        holder.txtDate.setText(schedule.getDate());
        holder.txtTime.setText(schedule.getTime());
        holder.txtLocation.setText(schedule.getLocation());

        holder.requestContainer.removeAllViews();

        List<ScheduleRequest> requests = schedule.getRequests();

        if (requests != null && !requests.isEmpty()) {
            holder.requestContainer.setVisibility(View.VISIBLE);

            for (int i = 0; i < requests.size(); i++) {
                ScheduleRequest request = requests.get(i);

                View requestRow = LayoutInflater.from(context).inflate(R.layout.request_row_layout, holder.requestContainer, false);

                TextView requestName = requestRow.findViewById(R.id.requestName);
                Button btnAccept = requestRow.findViewById(R.id.btnAccept);
                Button btnDecline = requestRow.findViewById(R.id.btnDecline);

                requestName.setText(request.getName());

                int finalI = i;

                btnAccept.setOnClickListener(v -> {
                    request.setStatus("accepted");
                    requests.remove(finalI);
                    notifyItemChanged(position);
                    Toast.makeText(context, request.getName() + " accepted", Toast.LENGTH_SHORT).show();
                });

                btnDecline.setOnClickListener(v -> {
                    request.setStatus("declined");
                    requests.remove(finalI);
                    notifyItemChanged(position);
                    Toast.makeText(context, request.getName() + " declined", Toast.LENGTH_SHORT).show();
                });

                holder.requestContainer.addView(requestRow);
            }
        } else {
            holder.requestContainer.setVisibility(View.GONE);
        }

        // Cancel schedule button
        holder.btnCancelSchedule.setOnClickListener(v -> {
            scheduleList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, scheduleList.size());
            Toast.makeText(context, schedule.getTitle() + " canceled", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDate, txtTime, txtLocation;
        Button btnCancelSchedule;
        LinearLayout requestContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            btnCancelSchedule = itemView.findViewById(R.id.btnCancelSchedule);
            requestContainer = itemView.findViewById(R.id.requestContainer);
        }
    }
}
