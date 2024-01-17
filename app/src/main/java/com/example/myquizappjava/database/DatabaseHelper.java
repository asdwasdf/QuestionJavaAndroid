package com.example.myquizappjava.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.myquizappjava.model.HighScore;
import com.example.myquizappjava.model.Questions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "quiz_database";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột của bảng Achievements
    public static final String TABLE_ACHIEVEMENTS = "achievements";
    public static final String COLUMN_ACHIEVEMENT_ID = "id";
    public static final String COLUMN_ACHIEVEMENT_NAME = "Name";
    public static final String COLUMN_ACHIEVEMENT_SCORE = "Score";

    // Tên bảng và cột của bảng Question
    public static final String TABLE_QUESTION = "question";
    public static final String COLUMN_QUESTION_ID = "id";

    public static final String COLUMN_QUESTION_NAME ="question";
    public static final String COLUMN_QUESTION_QUES1 = "Ques1";
    public static final String COLUMN_QUESTION_QUES2 = "Ques2";
    public static final String COLUMN_QUESTION_QUES3 = "Ques3";
    public static final String COLUMN_QUESTION_QUES4 = "Ques4";
    public static final String COLUMN_QUESTION_ANSWER = "Answer";
    public static final String COLUMN_QUESTION_IMAGE = "Image";

    // Câu lệnh tạo bảng Achievements
    private static final String CREATE_TABLE_ACHIEVEMENTS =
            "CREATE TABLE " + TABLE_ACHIEVEMENTS + "(" +
                    COLUMN_ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ACHIEVEMENT_NAME + " TEXT," +
                    COLUMN_ACHIEVEMENT_SCORE + " INTEGER" +
                    ")";

    // Câu lệnh tạo bảng QuestionAndAnswers
    private static final String CREATE_TABLE_QUESTION =
            "CREATE TABLE " + TABLE_QUESTION + "(" +
                    COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_QUESTION_NAME + " TEXT," +
                    COLUMN_QUESTION_IMAGE + " BLOB," +
                    COLUMN_QUESTION_QUES1 + " TEXT," +
                    COLUMN_QUESTION_QUES2 + " TEXT," +
                    COLUMN_QUESTION_QUES3 + " TEXT," +
                    COLUMN_QUESTION_QUES4 + " TEXT," +
                    COLUMN_QUESTION_ANSWER + " INTEGER" +
                    ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void insertUser(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACHIEVEMENT_NAME, "Achievement 1");
        values.put(COLUMN_ACHIEVEMENT_SCORE, 100);
        db.insert(TABLE_ACHIEVEMENTS, null, values);
        values.clear();
        values.put(COLUMN_ACHIEVEMENT_NAME, "Achievement 2");
        values.put(COLUMN_ACHIEVEMENT_SCORE, 200);
        db.insert(TABLE_ACHIEVEMENTS, null, values);
        values.clear();
        values.put(COLUMN_ACHIEVEMENT_NAME, "Achievement 3");
        values.put(COLUMN_ACHIEVEMENT_SCORE, 300);
        db.insert(TABLE_ACHIEVEMENTS, null, values);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng khi cơ sở dữ liệu được tạo lần đầu tiên
        db.execSQL(CREATE_TABLE_ACHIEVEMENTS);
        db.execSQL(CREATE_TABLE_QUESTION);
//        insertQuestions(db);
        insertUser(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        onCreate(db);
    }

    @SuppressLint("Range")

    public ArrayList<Questions> getAllQuestions() {
        ArrayList<Questions> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Questions question = new Questions();
                question.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_NAME)));
                question.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_IMAGE)));
                question.setOptionOne(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_QUES1)));
                question.setOptionTwo(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_QUES2)));
                question.setOptionThree(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_QUES3)));
                question.setOptionFour(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_QUES4)));
                question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ANSWER)));
                questionsList.add(question);
            } while (cursor.moveToNext());
            cursor.close();
//            getAllAchievements();
        }

        db.close();
        return questionsList;
    }
    @SuppressLint("Range")
    public ArrayList<HighScore> getAllAchievements(){
        ArrayList<HighScore> listHighScore = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACHIEVEMENTS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                HighScore highScore = new HighScore();
                highScore.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACHIEVEMENT_ID)));
                highScore.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACHIEVEMENT_NAME)));
                highScore.setScore(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACHIEVEMENT_SCORE)));
                listHighScore.add(highScore);
            } while (cursor.moveToNext());
            cursor.close();
            Log.d("aaa","a"+ listHighScore);
        }
        return listHighScore;
    }
    public void insertData(String question, String ques1, String ques2, String ques3, String ques4, Integer answer,Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        values.put(COLUMN_QUESTION_NAME, question);
        values.put(COLUMN_QUESTION_QUES1, ques1);
        values.put(COLUMN_QUESTION_QUES2, ques2);
        values.put(COLUMN_QUESTION_QUES3, ques3);
        values.put(COLUMN_QUESTION_QUES4, ques4);
        values.put(COLUMN_QUESTION_ANSWER, answer);
        values.put(COLUMN_QUESTION_IMAGE, byteArray);
        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    public void deleteQuestion(int questionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_QUESTION + " WHERE " + COLUMN_QUESTION_ID + " = ?";
        Object[] bindArgs = { questionId };
        db.execSQL(deleteQuery, bindArgs);
        db.close();
    }
}
