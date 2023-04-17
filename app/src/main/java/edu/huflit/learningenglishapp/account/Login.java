package com.example.learningenglishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView mtwResult;
    EditText medtname, medtpass;
    Button bttlogin, bttregister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttlogin = findViewById(R.id.BttLogin);
        bttregister = findViewById(R.id.BttRegister);
        mtwResult = findViewById(R.id.TwResult);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS Learningenglishapp (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, role TEXT)";
        db.execSQL(sql);

        bttlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClickLogin(View v) {
                String username = medtname.getText().toString();
                String password = medtpass.getText().toString();

                // Check if username and password match a record in the database
                String[] projection = { "id" };
                String selection = "username = ? AND password = ?";
                String[] selectionArgs = { username, password };
                Cursor cursor = db.query("Learningenglishapp", projection, selection, selectionArgs, null, null, null);

                // Move cursor to first row
                boolean result = cursor.moveToFirst();

                // Retrieve value of role column for currently logged-in user
                String roleUser = "";
                if (result) {
                    int columnIndex = cursor.getColumnIndex("role");
                    if (columnIndex >= 0) {
                        roleUser = cursor.getString(columnIndex);
                    }
                }

                // Close cursor and database connection
                cursor.close();
                db.close();

                // If username and password match, move to User or Admin activity
                if (result && roleUser.equals("User")) {
                    Intent intent = new Intent(Login.this, UserMain.class);
                    startActivity(intent);
                } else if (result && roleUser.equals("Admin")) {
                    Intent intent = new Intent(Login.this, AdminMain.class);
                    startActivity(intent);
                } else {
                    // Username and password do not match, display error message
                    mtwResult.setText("Incorrect username or password");
                }
            }
        });

        bttregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClickResign(View v) {
            startActivity(new Intent(Login.this, Register.class));}
        });
    }
}

