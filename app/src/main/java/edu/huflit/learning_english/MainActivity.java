package edu.huflit.learning_english;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHelper;
import edu.huflit.learning_english.javaClass.login;
import edu.huflit.learning_english.javaClass.register;

public class MainActivity extends AppCompatActivity {
    TextView mtwResult;
    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttlogin = findViewById(R.id.BttLogin);
        bttresigter = findViewById(R.id.BttRegister);
        mtwResult = findViewById(R.id.TwResult);


        dbHelper = new DBHelper(this);

        login login1 = new login();
        register register1 = new register();

        setContentView(R.layout.activity_login);


    }
}