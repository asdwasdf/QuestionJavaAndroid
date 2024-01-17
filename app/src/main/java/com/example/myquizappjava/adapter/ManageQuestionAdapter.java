package com.example.myquizappjava.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myquizappjava.R;
import com.example.myquizappjava.database.DatabaseHelper;
import com.example.myquizappjava.model.Questions;

import java.util.ArrayList;

public class ManageQuestionAdapter extends ArrayAdapter<Questions> {
    private Context mContext;
    private ArrayList<Questions> mQuestionList;

    private DatabaseHelper databaseHelper;

    public ManageQuestionAdapter(Context context, ArrayList<Questions> questionsList, DatabaseHelper databaseHelper) {
        super(context, R.layout.list_item_questions,questionsList);
        this.mContext = context;
        this.mQuestionList = questionsList;
        this.databaseHelper = databaseHelper;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_questions, parent, false);
        }

        ImageView imgFlag = listItem.findViewById(R.id.iv_flag);
        TextView questionsItem = listItem.findViewById(R.id.tv_question);
        Button btn_xoa_cart = listItem.findViewById(R.id.btn_delete);

        Questions question = mQuestionList.get(position);

//         Gán dữ liệu cho các thành phần
        questionsItem.setText(question.getQuestion());
        Bitmap bitmap = BitmapFactory.decodeByteArray(question.getImg(), 0, question.getImg().length);
        imgFlag.setImageBitmap(bitmap);


        btn_xoa_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions clickedQuestion = mQuestionList.get(position);
                databaseHelper.deleteQuestion(clickedQuestion.getId());
                mQuestionList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(mContext, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return listItem;
    }
}

