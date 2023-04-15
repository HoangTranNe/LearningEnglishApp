package com.example.learningenglishapp;

import static Database.DBHelper.COT_BLOGWRITING;
import static Database.DBHelper.COT_HEADLINE;
import static Database.DBHelper.COT_IDBLOG;
import static Database.DBHelper.COT_IDUSER;
import static Database.DBHelper.TAO_BANG_Blog;
import static Database.DBHelper.TEN_BANG_Result;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Mydatabase {
    SQLiteDatabase database;
    DBHelper helper;
    public Mydatabase(Context context) {
    DBHelper helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }
    public Cursor layTatCaDuLieu() {
        String[] cot = {
        DBHelper.COT_HEADLINE, COT_IDBLOG, DBHelper.COT_IDUSER, DBHelper.COT_BLOGWRITING,
        DBHelper.COT_TRIES, DBHelper.COT_IDTEST, DBHelper.COT_SCORE, DBHelper.COT_CHOICE,
        DBHelper.COT_DETAILS, DBHelper.COT_IDQUESTION, DBHelper.COT_DESCRIPTION,
        DBHelper.COT_AMOUNT,DBHelper.COT_LEVEL,DBHelper.COT_AGE,DBHelper.COT_GENDER,
        DBHelper.COT_PASSWORD,DBHelper.COT_ROLEUSER,DBHelper.COT_USERNAME
        };
        //lay het cot trong database
        Cursor cursor = null;
        cursor = database.query(DBHelper.
                        TEN_BANG_User, cot, null, null, null, null,
                DBHelper.COT_IDUSER + " DESC");
        cursor = database.query(DBHelper.
                        TEN_BANG_Test, cot, null, null, null, null,
                DBHelper.COT_IDTEST + " DESC");
        cursor = database.query(DBHelper.
                        TEN_BANG_Question, cot, null, null, null, null,
                DBHelper.COT_IDQUESTION + " DESC");
        cursor = database.query(DBHelper.
                        TEN_BANG_Exam, cot, null, null, null, null,
                DBHelper.COT_IDTEST + " DESC");
        cursor = database.query(DBHelper.
                        TEN_BANG_Result, cot, null, null, null, null,
                DBHelper.COT_IDUSER + " DESC");
        cursor = database.query(TAO_BANG_Blog, cot, null, null, null, null,
                COT_IDBLOG + " DESC");
        return cursor;
    }
    //Thêm + sửa bảng user (không có xóa vì chưa có tính năng xóa tài khoản
    public long them(User user) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_USERNAME,
                user.get_username());
        values.put(DBHelper.COT_PASSWORD,
                user.get_password());
        values.put(DBHelper.COT_AGE,
                user.get_age());
        values.put(DBHelper.COT_GENDER,
                user.get_gender());
        values.put(DBHelper.COT_LEVEL,
                user.get_level());
        values.put(DBHelper.COT_ROLEUSER,
                user.get_roleuser());
        return database.insert(DBHelper.
                TEN_BANG_User, null, values);
    }
    public long sua(User user) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_USERNAME,
                user.get_username());
        values.put(DBHelper.COT_PASSWORD,
                user.get_password());
        values.put(DBHelper.COT_AGE,
                user.get_age());
        values.put(DBHelper.COT_GENDER,
                user.get_gender());
        return database.update(DBHelper.TEN_BANG_User, values,
                DBHelper.COT_IDUSER + " = " + user.get_IDUser(), null);
    }

    //Thêm xóa sửa của bảng Exam
    public long them(Exam exam){
        String sql = "INSERT INTO Exam (_IDQuestion, _IDTest) VALUES (?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindLong(1, exam.get_Idquestion());
        statement.bindLong(2, exam.get_IDTest());
        statement.executeInsert();
        return statement.executeInsert();
    }
    public long sua(Exam exam){
        String sql = "UPDATE Exam SET _IDQuestion = ?, _IDTest = ? WHERE COT_IDQUESTION = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindLong(1, exam.get_Idquestion());
        statement.bindLong(2, exam.get_IDTest());
        statement.bindLong(3, exam.get_Idquestion());
        statement.executeUpdateDelete();
        return statement.executeUpdateDelete();
    }
    public long xoa(Exam exam){
        String sql = "DELETE FROM Exam WHERE COT_IDQUESTION = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindLong(1, exam.get_Idquestion());
        statement.executeUpdateDelete();
        return statement.executeUpdateDelete();
    }
    //Thêm xóa sửa bảng result
    public long them(Result result){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_IDUSER, result.get_IDUser());
        values.put(DBHelper.COT_IDTEST, result.get_IDExam());
        values.put(DBHelper.COT_TRIES, result.get_tries());
        values.put(DBHelper.COT_SCORE, result.get_score());
        return database.insert(TEN_BANG_Result, null, values);
    }
    public int xoa(long id){
        return database.delete(TEN_BANG_Result, COT_IDUSER + "=?", new String[]{String.valueOf(id)});
    }
    public int sua(Result result){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_IDUSER, result.get_IDUser());
        values.put(DBHelper.COT_IDTEST, result.get_IDExam());
        values.put(DBHelper.COT_TRIES, result.get_tries());
        values.put(DBHelper.COT_SCORE, result.get_score());
        return database.update(TEN_BANG_Result, values, COT_IDUSER + "=?", new String[]{String.valueOf(result.get_IDUser())});
    }
    //Thêm xóa sửa bảng BLOG
    public void them(Blog blog) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_HEADLINE, blog.get_headline());
        values.put(DBHelper.COT_BLOGWRITING, blog.get_blogwriting());
        database.insert(TAO_BANG_Blog, null, values);
    }
    public void xoa(int id) {
        database.delete(TAO_BANG_Blog, COT_IDBLOG + "=?", new String[]{String.valueOf(id)});
    }

    public int sua(Blog blog) {
        ContentValues values = new ContentValues();
        values.put(COT_HEADLINE, blog.get_headline());
        values.put(COT_BLOGWRITING, blog.get_blogwriting());
        return database.update(TAO_BANG_Blog, values, COT_IDBLOG + "=?", new String[]{String.valueOf(blog.get_IDblog())});
    }


}
