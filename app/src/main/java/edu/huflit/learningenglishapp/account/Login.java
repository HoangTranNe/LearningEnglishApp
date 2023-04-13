package edu.huflit.learningenglishapp.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBHelper;
import edu.huflit.learningenglishapp.R;
import edu.huflit.learningenglishapp.account.Register;
import edu.huflit.learningenglishapp.main_layout.User;

public class Login extends AppCompatActivity {

    TextView mtwResult;
    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
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

                // If username and password match, move to User activity
                if (result) {
                    Intent intent = new Intent(Login.this, User.class);
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
            startActivity(new Intent(Login.this, Register.class));
        });
    }
}
