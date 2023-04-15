package com.example.learningenglishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText ageEditText;
    private RadioGroup genderGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private TextView resultTextView;
    private Button registerButton;
   
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.EdtName);
        passwordEditText = findViewById(R.id.EdtPass);
        confirmPasswordEditText = findViewById(R.id.EdtRepass);
        ageEditText = findViewById(R.id.EdtAge);
        genderGroup = findViewById(R.id.GenderGroup);
        maleRadioButton = findViewById(R.id.BttNam);
        femaleRadioButton = findViewById(R.id.BttNu);
        resultTextView = findViewById(R.id.TwFinish);
        registerButton = findViewById(R.id.BttResign);

        // Create or open the database
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS Learningenglishapp (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, age INTEGER, gender TEXT, roleuser TEXT)";
        db.execSQL(sql);

        // Set onClickListener for register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String ageString = ageEditText.getText().toString();
                int age = TextUtils.isEmpty(ageString) ? 0 : Integer.parseInt(ageString);
                String gender = "";
                int genderId = genderGroup.getCheckedRadioButtonId();
                if (genderId == R.id.BttNam) {
                    gender = "Nam";
                } else if (genderId == R.id.BttNu) {
                    gender = "Nữ";
                }

                // Check if username already exists in database
                String[] projection = { "id" };
                String selection = "username = ?";
                String[] selectionArgs = { username };
                Cursor cursor = db.query("Learningenglishapp", projection, selection, selectionArgs, null, null, null);

                // If username already exists, display error message and return
                if (cursor.moveToFirst()) {
                    resultTextView.setText("Username already exists");
                    return;
                }

                // Check if password and confirmPassword match
                if (!password.equals(confirmPassword)) {
                    resultTextView.setText("Passwords do not match");
                    return;
                }

                // Add user information to database
                ContentValues values = new ContentValues();
                values.put("username", username);
                values.put("password", password);
                values.put("age", age);
                values.put("gender", gender);
                values.put("roleuser", "User");
                db.insert("Learningenglishapp", null, values);

                // chuyển trang khi thanh công
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
