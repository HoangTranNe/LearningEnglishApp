package edu.huflit.learningenglishapp;

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
        DBHelper.COT_HEADLINE, DBHelper.COT_IDBLOG, DBHelper.COT_IDUSER, DBHelper.COT_BLOGWRITING,
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
        cursor = database.query(DBHelper.
                        TAO_BANG_Blog, cot, null, null, null, null,
                DBHelper.COT_IDBLOG + " DESC");
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
}
