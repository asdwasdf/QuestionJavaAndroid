package com.example.myquizappjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myquizappjava.R;
import com.example.myquizappjava.database.DatabaseHelper;
import com.example.myquizappjava.model.HighScore;

import java.util.ArrayList;

public class HighScoreAdapter extends ArrayAdapter<HighScore> {

    private final Context mContext;
    private ArrayList<HighScore> highScore;
    private DatabaseHelper databaseHelper;

    public HighScoreAdapter(Context context, ArrayList<HighScore> highScore, DatabaseHelper databaseHelper) {
        super(context, R.layout.list_item_highscore,highScore);
        this.mContext = context;
        this.databaseHelper = databaseHelper;
        this.highScore = highScore;
    }

    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_highscore, parent, false);
        }
        TextView tv_name = listItem.findViewById(R.id.tv_name);
        TextView tv_score = listItem.findViewById(R.id.tv_score);
        HighScore currenHighscore =highScore.get(position);
        tv_name.setText(currenHighscore.getName());
        tv_score.setText(String.valueOf(currenHighscore.getScore()));
        return listItem;
    }
}
