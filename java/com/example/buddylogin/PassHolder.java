package com.example.buddylogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PassHolder extends AppCompatActivity {

    RecyclerView recyclerSchedules;
    ScheduleAdapter adapter;
    List<Schedule> scheduleList;

    Button btnViewSchedules;
    Button btnCreateSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new schedule
        btnCreateSchedule = findViewById(R.id.btnCreateSchedule);
        btnCreateSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(PassHolder.this, Schedules.class);
            startActivity(intent);
        });

        // View schedules
        btnViewSchedules = findViewById(R.id.btnViewSchedules);
        btnViewSchedules.setOnClickListener(v -> {
            Intent intent = new Intent(PassHolder.this, ViewSchedules.class);
            startActivity(intent);
        });

        // RecyclerView setup
        recyclerSchedules = findViewById(R.id.recyclerSchedules);
        recyclerSchedules.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule("Weight Training", "22/03/2025", "12:00pm", "Centurion Gate", "No requests yet"));
        scheduleList.add(new Schedule("CrossFit", "25/03/2025", "6:00am", "Centurion Gate", "2 Requests"));
        scheduleList.add(new Schedule("HIIT", "29/03/2025", "5:00pm", "Centurion Gate", "1 Request"));
        scheduleList.add(new Schedule("Cardio", "30/03/2025", "7:00am", "Centurion Gate", "No requests yet"));

        // Adapter
        adapter = new ScheduleAdapter(this, scheduleList);
        recyclerSchedules.setAdapter(adapter);
    }
}
