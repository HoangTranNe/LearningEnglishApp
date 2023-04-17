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

        // khai báo các button text view và button
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
        bttRegister.setOnClickListener(view ->
        {
            startActivity(new Intent(login.this, register.class));
        });


        // cái này trong onCreate á
         /*edtName = findViewById(R.id.EdtName);
         edtPass = findViewById(R.id.EdtPass);
         bttLogin = findViewById(R.id.BttLogin);
         bttRegister = findViewById(R.id.BttRegister);
         mtwResult = findViewById(R.id.TwResult);

        dbHelper = new DBHelper(this);

        bttLogin.setOnClickListener(this::setBttLogin);
        bttRegister.setOnClickListener(this::setBttRegister);

        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        bttRegister.setOnClickListener(v -> {
            String username = (edtName).getText().toString();
            String password = (edtPass).getText().toString();
            viewModel.setName(username);
            viewModel.setPassword(password);
            viewModel.registerUser();
        });*/


        // khúc này phương thức riêng nha


       /* edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        viewModel.getName().observe(this, s -> {
            TextView twResult = findViewById(R.id.TwResult);
            twResult.setText("Tên đăng nhập: " + s);
        });
        viewModel.getPassword().observe(this, s -> {
            TextView twPass = findViewById(R.id.TwPass);
            twPass.setText("Mật khẩu: " + s);
        });

    }
    public class MyViewModel extends ViewModel {

        public void registerUser(){
            DataH user = new User();
        }

        private MutableLiveData<String> name = new MutableLiveData<>();
        private MutableLiveData<String> password = new MutableLiveData<>();

        public void setName(String input) {
            name.setValue(input);
        }

        public MutableLiveData<String> getName() {
            return name;
        }

        public void setPassword(String input) {
            password.setValue(input);
        }

        public MutableLiveData<String> getPassword() {
            return password;
        }
*/
    }

    // cái phần ở dưới là t có thử làm theo cách của t thấy oke có thể thử nhưng vẫn còn lỗi vài chỗ
   /* private void onLoginButtonClick(){
        String username = edtName.getText().toString();
        String password = edtPass.getText().toString();
        try( SQLiteDatabase db = dbHelper.getWritableDatabase();
             Cursor cursor = findUser(db, username, password);){
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

    public void setBttRegister(View view) {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
    }
    public void setBttLogin(View view){
        onLoginButtonClick();
    }*/

}
