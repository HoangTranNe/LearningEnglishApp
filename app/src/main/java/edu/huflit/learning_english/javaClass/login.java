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
    EditText edtName,edtPass;
    Button bttLogin,bttRegister;
    TextView mtwResult;
    private DBHelper dbHelper;

    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         edtName = findViewById(R.id.EdtName);
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
        });

        edtName.addTextChangedListener(new TextWatcher() {
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

    }
    private void onLoginButtonClick(){
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
    }


}
