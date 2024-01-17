package com.example.myquizappjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myquizappjava.database.DatabaseHelper;
import com.example.myquizappjava.model.Constants;
import com.example.myquizappjava.model.Questions;

import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private int mCurrentPosition = 1;
    private ArrayList<Questions> mQuestionList = null;
    private int mSelectedOptionPosition = 0;
    private int mCorrectAnswer = 0;
    private ProgressBar progressBar;
    private TextView tvProgress, tvQuestion, tvOptionOne, tvOptionTwo, tvOptionThree, tvOptionFour;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private ImageView ivImage;
    private Button btnSubmit;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tv_progress);
        tvQuestion = findViewById(R.id.tv_question);
        ivImage = findViewById(R.id.iv_image);
        tvOptionOne = findViewById(R.id.tv_optionone);
        tvOptionTwo = findViewById(R.id.tv_optiontwo);
        tvOptionThree = findViewById(R.id.tv_optionthree);
        tvOptionFour = findViewById(R.id.tv_optionfour);
        btnSubmit = findViewById(R.id.btn_submit);

        mUserName = getIntent().getStringExtra(Constants.USER_NAME);
        mQuestionList= databaseHelper.getAllQuestions();
        tvOptionOne.setOnClickListener(this);
        tvOptionTwo.setOnClickListener(this);
        tvOptionThree.setOnClickListener(this);
        tvOptionFour.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        setQuestion();
    }

    private void setQuestion() {
        defaultOptionView();
        Questions question = mQuestionList.get(mCurrentPosition - 1);
        progressBar.setProgress(mCurrentPosition);
        tvProgress.setText(mCurrentPosition + "/" + progressBar.getMax());
        tvQuestion.setText(question.getQuestion());
        Bitmap bitmap = BitmapFactory.decodeByteArray(question.getImg(), 0, question.getImg().length);
        ivImage.setImageBitmap(bitmap);
        tvOptionOne.setText(question.getOptionOne());
        tvOptionTwo.setText(question.getOptionTwo());
        tvOptionThree.setText(question.getOptionThree());
        tvOptionFour.setText(question.getOptionFour());

        if (mCurrentPosition == mQuestionList.size()) {
            btnSubmit.setText("FINISH");
        } else {
            btnSubmit.setText("SUBMIT");
        }
    }

    private void defaultOptionView() {
        ArrayList<TextView> options = new ArrayList<>();
        options.add(0, tvOptionOne);
        options.add(1, tvOptionTwo);
        options.add(2, tvOptionThree);
        options.add(3, tvOptionFour);

        for (TextView option : options) {
            option.setTextColor(Color.parseColor("#7A8089"));
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        }
    }

    private void selectedOptionView(TextView tv, int selectedOptionNum) {
        defaultOptionView();

        mSelectedOptionPosition = selectedOptionNum;
        tv.setTextColor(Color.parseColor("#364A43"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.select_option_border_bg));
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.tv_optionone) {
            selectedOptionView(tvOptionOne, 1);
        } else if (viewId == R.id.tv_optiontwo) {
            selectedOptionView(tvOptionTwo, 2);
        } else if (viewId == R.id.tv_optionthree) {
            selectedOptionView(tvOptionThree, 3);
        } else if (viewId == R.id.tv_optionfour) {
            selectedOptionView(tvOptionFour, 4);
        } else if (viewId == R.id.btn_submit) {
            if (mSelectedOptionPosition == 0) {
                mCurrentPosition++;
                if (mCurrentPosition <= mQuestionList.size()) {
                    setQuestion();
                } else {
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra(Constants.USER_NAME, mUserName);
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList.size());
                    intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer);
                    startActivity(intent);
                    finish();
                }
            } else {
                Questions question = mQuestionList.get(mCurrentPosition - 1);
                if (question.getCorrectAnswer() != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg);
                } else {
                    mCorrectAnswer++;
                }
                answerView(question.getCorrectAnswer(), R.drawable.correct_option_border_bg);

                if (mCurrentPosition == mQuestionList.size()) {
                    btnSubmit.setText("FINISH");
                } else {
                    btnSubmit.setText("GO TO THE NEXT QUESTION");
                }
                mSelectedOptionPosition = 0;
            }
        }

    }

    private void answerView(int answer, int drawable) {
        switch (answer) {
            case 1:
                tvOptionOne.setBackground(ContextCompat.getDrawable(this, drawable));
                break;
            case 2:
                tvOptionTwo.setBackground(ContextCompat.getDrawable(this, drawable));
                break;
            case 3:
                tvOptionThree.setBackground(ContextCompat.getDrawable(this, drawable));
                break;
            case 4:
                tvOptionFour.setBackground(ContextCompat.getDrawable(this, drawable));
                break;
        }
    }
}
