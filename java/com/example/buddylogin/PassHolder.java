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
    List<PassHolderSchedule> scheduleList;

    Button btnViewSchedules;
    Button btnCreateSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passholder_activity);

        // Create and view schedule buttons
        btnCreateSchedule = findViewById(R.id.btnCreateSchedule);
        btnViewSchedules = findViewById(R.id.btnViewSchedules);

         btnCreateSchedule.setOnClickListener(v -> {
             Intent intent = new Intent(PassHolder.this, Schedule.class);
             startActivity(intent);
         });

        btnViewSchedules.setOnClickListener(v -> {
            Intent intent = new Intent(PassHolder.this, ViewSchedule.class);
            startActivity(intent);
        });

        // RecyclerView setup
        recyclerSchedules = findViewById(R.id.recyclerSchedules);
        recyclerSchedules.setLayoutManager(new LinearLayoutManager(this));

        // Sample schedules 
        scheduleList = new ArrayList<>();

        List<ScheduleRequest> emptyRequests = new ArrayList<>();

        List<ScheduleRequest> requests1 = new ArrayList<>();
        requests1.add(new ScheduleRequest("req1", "John Doe", "pending"));
        requests1.add(new ScheduleRequest("req2", "Jane Smith", "pending"));

        List<ScheduleRequest> requests2 = new ArrayList<>();
        requests2.add(new ScheduleRequest("req3", "Alice Brown", "pending"));

        // Adding schedules
        scheduleList.add(new PassHolderSchedule("Weight Training", "22/03/2025", "12:00pm", "Centurion Gate", emptyRequests));
        scheduleList.add(new PassHolderSchedule("CrossFit", "25/03/2025", "6:00am", "Centurion Gate", requests1));
        scheduleList.add(new PassHolderSchedule("HIIT", "29/03/2025", "5:00pm", "Centurion Gate", requests2));
        scheduleList.add(new PassHolderSchedule("Cardio", "30/03/2025", "7:00am", "Centurion Gate", emptyRequests));

        // Adapter
        adapter = new ScheduleAdapter(this, scheduleList);
        recyclerSchedules.setAdapter(adapter);
    }
}
