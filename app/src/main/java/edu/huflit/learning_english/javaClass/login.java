package edu.huflit.learning_english.javaClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import Database.DBHelper;
import edu.huflit.learning_english.R;

public class login extends AppCompatActivity {
    private TextView mtwResult;
    private EditText medtname;
    private EditText medtpass;

    private Button bttLogin, bttRegister;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttLogin = findViewById(R.id.BttLogin);
        bttRegister = findViewById(R.id.BttRegister);
        mtwResult = findViewById(R.id.TwResult);

        dbHelper = new DBHelper(this);

        bttLogin.setOnClickListener(v -> loginUser());
        bttRegister.setOnClickListener(v -> startActivity(new Intent(login.this, register.class)));

    }

    private void loginUser(){
        onLoginButtonClick();
    }
    public void onLoginButtonClick(){
        String username = medtname.getText().toString();
        String password = medtpass.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = findUser(db, username, password);

        if (cursor.moveToFirst()) {
            String roleUser = getRoleUser(cursor);

            if (Objects.equals(roleUser, "User")) {
                startActivity(new Intent(login.this, user.class));
            } else if (Objects.equals(roleUser, "Admin")) {
                startActivity(new Intent(login.this, admin.class));
            }
        }
        else {
            mtwResult.setText("Incorrect username or password");
        }
    }
    private Cursor findUser(SQLiteDatabase db, String username, String password) {
        String[] projection = {"_role_user"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        return db.query("Learningenglishapp", projection, selection, selectionArgs, null, null, null);
    }


    private String getRoleUser(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("_role_user");
        return (columnIndex >= 0) ? cursor.getString(columnIndex) : null;
    }

}
