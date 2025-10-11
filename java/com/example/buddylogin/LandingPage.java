package com.example.buddylogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {

    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        //LoginActivity
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPage.this, LoginActivity.class);
            startActivity(intent);
        });

        //RegisterActivity
        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPage.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
