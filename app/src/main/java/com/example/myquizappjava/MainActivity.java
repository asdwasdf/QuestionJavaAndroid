package com.example.myquizappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizappjava.model.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCreateQues = findViewById(R.id.btn_create_question);
        Button btnStart = findViewById(R.id.btn_start);
        EditText etName = findViewById(R.id.et_name);
        Button btnHighScore = findViewById(R.id.btn_high_score);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizQuestionActivity.class);
                    intent.putExtra(Constants.USER_NAME, etName.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        btnCreateQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageQuestionsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
