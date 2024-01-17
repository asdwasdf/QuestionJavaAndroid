package com.example.myquizappjava;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizappjava.adapter.HighScoreAdapter;
import com.example.myquizappjava.database.DatabaseHelper;
import com.example.myquizappjava.model.HighScore;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {
    ListView hscListview;
    HighScoreAdapter highScoreAdapter;
    ArrayList<HighScore> highScoresList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        hscListview = findViewById(R.id.hsc_lv);

            databaseHelper =    new DatabaseHelper(this);

            highScoresList = databaseHelper.getAllAchievements();

            highScoreAdapter = new HighScoreAdapter(this,highScoresList,databaseHelper);

            hscListview.setAdapter(highScoreAdapter);

    }
}