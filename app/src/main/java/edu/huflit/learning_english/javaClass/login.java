package edu.huflit.learning_english.javaClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import Database.DBHelper;
import edu.huflit.learning_english.R;

public class login extends AppCompatActivity {

    TextView mtwResult;
    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttlogin = findViewById(R.id.BttLogin);
        bttresigter = findViewById(R.id.BttResig);
        mtwResult = findViewById(R.id.TwResult);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS Learningenglishapp (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";
        db.execSQL(sql);
        // khai báo các button text view và button

        bttlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = medtname.getText().toString();
                String password = medtpass.getText().toString();

                // Check if username and password match a record in the database
                String[] projection = { "id" };
                String selection = "username = ? AND password = ?";
                String[] selectionArgs = { username, password };
                Cursor cursor = db.query("Learningenglishapp", projection, selection, selectionArgs, null, null, null);

                // Move cursor to first row
                boolean result = cursor.moveToFirst();

                // Close cursor
                cursor.close();

                String[] projection2 = { "_roleuser" };
                String selection2 = "username = ?";
                String[] selectionArgs2 = { username, password  };
                Cursor cursor2 = db.query("Learningenglishapp", projection2, selection2, selectionArgs2, null, null, null);



                // Retrieve value of _roleuser column for currently logged-in user
                int columnIndex = cursor2.getColumnIndex("_roleuser");
                if (columnIndex < 0) {
                    // Column not found, handle error
                    return;
                }
                String roleUser = cursor2.getString(columnIndex);

                // If username and password match, move to User activity
                if (result && Objects.equals(roleUser, "User")) {
                    Intent intent = new Intent(login.this, user.class);
                    startActivity(intent);
                } else if (result && Objects.equals(roleUser, "Admin")) {
                    Intent intent = new Intent(login.this,admin.class);
                    startActivity(intent);

                } else {
                    // Username and password do not match, display error message
                    mtwResult.setText("Incorrect username or password");
                }
                cursor.close();
                db.close();
            }
        });
        bttresigter.setOnClickListener(view -> {
            startActivity(new Intent(login.this, register.class));
        });
    }
}
