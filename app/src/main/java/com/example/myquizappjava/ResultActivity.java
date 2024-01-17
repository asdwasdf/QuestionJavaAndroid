package com.example.myquizappjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizappjava.model.Constants;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvScore = findViewById(R.id.tv_score);
        Button btnFinish = findViewById(R.id.btn_finish);

        tvName.setText(getIntent().getStringExtra(Constants.USER_NAME));
        int totalQuestions = getIntent().getIntExtra(Constants.TOTAL_QUESTIONS, 0);
        int correctAnswer = getIntent().getIntExtra(Constants.CORRECT_ANSWER, 0);

        tvScore.setText("Your score is " + correctAnswer + " out of " + totalQuestions);

        btnFinish.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
        });
    }
}
