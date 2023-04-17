package edu.huflit.learning_english.javaClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import Database.User;
import edu.huflit.learning_english.databinding.ActivityLoginBinding;

import java.io.Closeable;
import java.util.Objects;

import Database.DBHelper;
import edu.huflit.learning_english.R;

public class login extends AppCompatActivity {
    EditText edtName, edtPass;
    Button bttLogin, bttRegister;
    TextView mtwResult;
    DBHelper dbHelper;



    //khúc này định truy vấn từ user mà hơi lỏ
    /*User user = new User();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Create the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS Learningenglishapp (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";
        db.execSQL(sql);


    }

    public void bttLogin(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        bttLogin.setOnClickListener(v ->
        {
            String username = edtName.getText().toString();
            String password = edtPass.getText().toString();

            // Check if username and password match a record in the database
            String[] projection = {"id"};
            String selection = "username = ? AND password = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query("Learningenglishapp", projection, selection, selectionArgs, null, null, null);

            // Move cursor to first row
            boolean result = cursor.moveToFirst();

            // Close cursor
            cursor.close();

            String[] projection2 = {"_roleuser"};
            String selection2 = "username = ?";
            String[] selectionArgs2 = {username, password};
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
                Intent intent = new Intent(login.this, admin.class);
                startActivity(intent);

            } else {
                // Username and password do not match, display error message
                mtwResult.setText("Incorrect username or password");
            }
            cursor.close();
            db.close();
        });
    }
    public void bttRegister(View view){
        bttRegister.setOnClickListener(v ->
        {
            startActivity(new Intent(login.this, register.class));
        });
    }



}
