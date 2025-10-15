package com.example.buddylogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewSchedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewScheduleAdapter adapter;
    private List<PassHolderSchedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_schedule);

        recyclerView = findViewById(R.id.recyclerViewSchedules);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        schedules = new ArrayList<>();
        schedules.add(new PassHolderSchedule(
                "Weight Training",
                "22/03/2025",
                "12:00pm",
                "Centurion Gate",
                new ArrayList<>()
        ));
        schedules.add(new PassHolderSchedule(
                "Cardio",
                "30/03/2025",
                "7:00am",
                "Centurion Gate",
                new ArrayList<>()
        ));

        // Adapter setup with Update/Delete actions
        adapter = new ViewScheduleAdapter(this, schedules, new ViewScheduleAdapter.OnItemActionListener() {
            @Override
            public void onUpdate(PassHolderSchedule schedule) {
                Intent intent = new Intent(ViewSchedule.this, UpdateSchedule.class);
                intent.putExtra("index", schedules.indexOf(schedule));
                intent.putExtra("title", schedule.getTitle());
                intent.putExtra("date", schedule.getDate());
                intent.putExtra("time", schedule.getTime());
                intent.putExtra("location", schedule.getLocation());
                startActivityForResult(intent, 1);
            }

            @Override
            public void onDelete(PassHolderSchedule schedule) {
                schedules.remove(schedule);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    // Handles updated schedule
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int index = data.getIntExtra("index", -1);
            if (index != -1 && index < schedules.size()) {
                String updatedTitle = data.getStringExtra("title");
                String updatedDate = data.getStringExtra("date");
                String updatedTime = data.getStringExtra("time");
                String updatedLocation = data.getStringExtra("location");

                // Update the item in the list
                PassHolderSchedule schedule = schedules.get(index);
                schedule.setTitle(updatedTitle);
                schedule.setDate(updatedDate);
                schedule.setTime(updatedTime);
                schedule.setLocation(updatedLocation);

                // Refresh RecyclerView item
                adapter.notifyItemChanged(index);
            }
        }
    }
}
