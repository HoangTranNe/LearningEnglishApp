package com.example.learningenglishapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class resigster extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText AgeEditText;
    private RadioGroup mGenderGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private TextView resultTextView;
    private Button registerButton;
   
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resigster);

        usernameEditText = findViewById(R.id.EdtName);
        passwordEditText = findViewById(R.id.EdtPass);
        confirmPasswordEditText = findViewById(R.id.EdtRepass);
        AgeEditText = findViewById(R.id.EdtAge);
        mGenderGroup = findViewById(R.id.GenderGroup);
        maleRadioButton = findViewById(R.id.BttNam);
        femaleRadioButton = findViewById(R.id.BttNu);
        resultTextView = findViewById(R.id.TwFinish);
        registerButton = findViewById(R.id.BttResign);

        // Create or open the database
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS Learningenglishapp (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, phoneNumber TEXT, gender TEXT)";
        db.execSQL(sql);

        // Set onClickListener for register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                int Age = Integer.parseInt(AgeEditText.getText().toString());
                String gender = "";
                int genderId = mGenderGroup.getCheckedRadioButtonId();
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

                // Move cursor to first row
                boolean result = cursor.moveToFirst();

                // If username already exists, display error message and return
                if (result) {
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
                values.put("_username", username);
                values.put("_password", password);
                values.put("_age", Age);
                values.put("_gender", gender);
                values.put("_roleuser", "User");
                db.insert("Learningenglishapp", null, values);

                // chuyển trang khi thanh công
                Toast.makeText(resigster.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
