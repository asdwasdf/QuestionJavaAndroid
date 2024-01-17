package com.example.myquizappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myquizappjava.adapter.ManageQuestionAdapter;
import com.example.myquizappjava.database.DatabaseHelper;
import com.example.myquizappjava.model.Questions;

import java.util.ArrayList;

public class ManageQuestionsActivity extends AppCompatActivity {
    private ListView questionsListView;
    private ArrayList<Questions> questionsItem;
    DatabaseHelper databaseHelper;
    private Button btnReturn;
    private Button btnAdd ;

    private ManageQuestionAdapter manageQuestionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_questions);
        questionsListView = findViewById(R.id.lv_questions);
        btnAdd = findViewById(R.id.btn_add);
        btnReturn = findViewById(R.id.btn_return);
        databaseHelper = new DatabaseHelper(this);
        questionsItem = databaseHelper.getAllQuestions();
        manageQuestionAdapter = new ManageQuestionAdapter(this,questionsItem,databaseHelper);
        questionsListView.setAdapter(manageQuestionAdapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageQuestionsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view){
                startActivity(new Intent(ManageQuestionsActivity.this,CreateQuestionActivity.class));
            }
        });
    }
}