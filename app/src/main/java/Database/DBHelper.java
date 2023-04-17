package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // tên database
    private static final String TEN_DATABASE= "Learningenglishapp";
    // bảng user
    public static final String TEN_BANG_User = "User";

    /*public static long COT_ID_USER = null; t note ở đây là có thể xem xét trường hợp này*/

    public static final String COT_ID_USER = "_iduser";
    public static final String COT_USERNAME = "_username";
    public static final String COT_PASSWORD= "_password";
    public static final String COT_GENDER= "_gender";
    public static final String COT_ROLEUSER= "_roleuser";
    public static final String COT_LEVEL = "_level";
    public static final String COT_AGE = "_age";

    private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TEN_BANG_User;
    private static final String TAO_BANG_User = ""
            + "create table " + TEN_BANG_User + " ( "
            + COT_ID_USER + " integer primary key autoincrement ,"
            + COT_USERNAME + " text not null, "
            + COT_PASSWORD + " text not null, "
            + COT_GENDER + "text not null, "
            + COT_ROLEUSER + "text not null, "
            + COT_LEVEL + "text not null, "
            + COT_AGE + "integer not null );";
    //bảng test
    public static final String TEN_BANG_Test = "Test";

    public static final String COT_IDTEST = "_IDTest";
    public static final String COT_DESCRIPTION = "_description";
    public static final String COT_AMOUNT= "_amount";

    private static final String TAO_BANG_Test = ""
            + "create table " + TEN_BANG_Test + " ( "
            + COT_IDTEST + " integer primary key autoincrement ,"
            + COT_DESCRIPTION + " text not null, "
            + COT_LEVEL + "text not null, "
            + COT_AMOUNT + "integer not null );";
    //bảng question
    public static final String TEN_BANG_Question = "Question";
    public static final String COT_IDQUESTION = "_IDQuestion";
    public static final String COT_DETAILS = "_details";
    public static final String COT_CHOICE= "_choice";
    public static final String COT_SCORE = "_score";

    private static final String TAO_BANG_Question = ""
            + "create table " + TEN_BANG_Question + " ( "
            + COT_IDQUESTION + " integer primary key autoincrement ,"
            + COT_DETAILS + " text not null, "
            + COT_CHOICE + "text not null, "
            + COT_SCORE + "integer not null );";
    //bảng exam
    public static final String TEN_BANG_Exam = "Exam";
    private static final String TAO_BANG_Exam = ""
            + "create table " + TEN_BANG_Exam + " ( "
            + COT_IDQUESTION + " integer primary key autoincrement ,"
            + COT_IDTEST + " integer primary key autoincrement ,"
            +"FOREIGN KEY(_IDQuestion)REFERENCES TEN_BANG_Question(_IDQuestion)  ,"
            +"FOREIGN KEY(_IDTest)REFERENCES TAO_BANG_Test(_IDTest));";

    //bảng result
    public static final String TEN_BANG_Result = "Result";
    public static final String COT_TRIES ="_Tries";

    private static final String TAO_BANG_Result = ""
            + "create table " + TEN_BANG_Result + " ( "
            + COT_ID_USER + " integer primary key autoincrement ,"
            + COT_IDTEST + " integer primary key autoincrement ,"
            + COT_TRIES + "integer not null, "
            + COT_SCORE + "integer not null );";
    //bảng blog
   public static final String TAO_BANG_Blog = "Blog";
   public static final String COT_IDBLOG = "_IDBlog";
   public static final String COT_HEADLINE = "_headline";
   public static final String COT_BLOGWRITING = "_blogwriting";

    private static final String TAO_BANG_BLOG = ""
            + "create table " + TAO_BANG_Blog + " ( "
            + COT_IDBLOG + " integer primary key autoincrement ,"
            + COT_HEADLINE + " text not null, "
            + COT_BLOGWRITING + "text not null );";

    public DBHelper(Context context) {
        super(context, TEN_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_User);
        db.execSQL(TAO_BANG_Test);
        db.execSQL(TAO_BANG_Question);
        db.execSQL(TAO_BANG_Exam);
        db.execSQL(TAO_BANG_Result);
        db.execSQL(TAO_BANG_BLOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_USER);
        onCreate(db);
    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_USERNAME, user.get_username());
        values.put(COT_PASSWORD, user.get_password());
        db.insert(TEN_BANG_User, null, values);
        db.close();
    }

    // còn cái này là lấy thông tin user nó vẫn bị lỗi chưa biết cách sửa
    /*public User getUser(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {String.valueOf(COT_ID_USER), COT_USERNAME, COT_PASSWORD};
        String selection = COT_USERNAME + "=? and " + COT_PASSWORD + "=?";
        String[] selectionArgs = {name, password};
        Cursor cursor = db.query(TEN_BANG_User, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(COT_ID_USER));
            String username = cursor.getString(cursor.getColumnIndex(COT_USERNAME == "_username"));
            String pass = cursor.getString(cursor.getColumnIndex(COT_PASSWORD));
            User user = new User(COT_ID_USER.longValue(), username, pass);
            cursor.close();
            return user;
        }
        cursor.close();
        return null;
    }*/
}
