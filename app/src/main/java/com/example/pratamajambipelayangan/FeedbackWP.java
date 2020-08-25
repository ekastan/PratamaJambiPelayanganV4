package com.example.pratamajambipelayangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class FeedbackWP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        RelativeLayout rlkritik = findViewById(R.id.rlkritik);
        RelativeLayout rlkuisioner = findViewById(R.id.rlkuisioner);

        rlkritik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedbackWP.this, Kritik.class);
                startActivity(i);
            }
        });

        rlkuisioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(FeedbackWP.this, KuesionerWP.class);
                startActivity(i);
            }
        });
    }
}
