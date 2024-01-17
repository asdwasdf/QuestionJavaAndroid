package com.example.myquizappjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myquizappjava.database.DatabaseHelper;

import java.io.IOException;

public class CreateQuestionActivity extends AppCompatActivity {
    private Bitmap selectedImage;
    private EditText editTextQuestionName;
    private ImageView imageView;
    private EditText editTextQues1;
    private EditText editTextQues2;
    private EditText editTextQues3;
    private EditText editTextQues4;
    private EditText editTextAnswer;
    private Button selectImageButton;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        editTextQuestionName = findViewById(R.id.editTextQuestionName);
        editTextQues1 = findViewById(R.id.editTextQues1);
        editTextQues2 = findViewById(R.id.editTextQues2);
        editTextQues3 = findViewById(R.id.editTextQues3);
        editTextQues4 = findViewById(R.id.editTextQues4);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        imageView = findViewById(R.id.imageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        buttonAdd = findViewById(R.id.buttonAdd);
        Button btnReturn = findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateQuestionActivity.this,ManageQuestionsActivity.class));
            }
        });
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionName = editTextQuestionName.getText().toString().trim();
                String ques1 = editTextQues1.getText().toString().trim();
                String ques2 = editTextQues2.getText().toString().trim();
                String ques3 = editTextQues3.getText().toString().trim();
                String ques4 = editTextQues4.getText().toString().trim();
                String answerString = editTextAnswer.getText().toString().trim();
                if (questionName.isEmpty() || ques1.isEmpty() || ques2.isEmpty() || ques3.isEmpty() || ques4.isEmpty() || selectedImage == null || answerString.isEmpty()) {
                    Toast.makeText(CreateQuestionActivity.this, "Please enter sufficient fields", Toast.LENGTH_LONG).show();
                } else {
                    Integer answer = Integer.parseInt(answerString);
                    databaseHelper.insertData(questionName, ques1, ques2, ques3, ques4, answer, selectedImage);
                    Toast.makeText(CreateQuestionActivity.this, "done", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}