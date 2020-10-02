package com.example.pratamajambipelayangan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToLayananOnline (View view)    {
        Intent layananOnline = new Intent(MainActivity.this, LayananOnline.class);

        startActivity(layananOnline);
    }

    public void goToFeedback (View view)    {
        Intent feedbackWP = new Intent(MainActivity.this, FeedbackWP.class);

        startActivity(feedbackWP);
    }

    public void goToTutorial (View view)    {
        Intent tutorial = new Intent(MainActivity.this, TutorialMaster.class);

        startActivity(tutorial);
    }

    public void goToMaps (View view)    {
        Intent maps = new Intent(MainActivity.this, MapsActivity.class);

        startActivity(maps);
    }

    public void goToPersyratan (View view)    {
        Intent persyaratan = new Intent(MainActivity.this, Persyaratan.class);

        startActivity(persyaratan);
    }
}
