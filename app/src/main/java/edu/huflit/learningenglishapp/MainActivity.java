package edu.huflit.learningenglishapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;

import Database.User;
import edu.huflit.learningenglishapp.account.Login;


public class MainActivity extends AppCompatActivity{

    ImageButton back, user,history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = (ImageButton) findViewById(R.id.backBTN);
        user = (ImageButton) findViewById(R.id.user);
        history = (ImageButton) findViewById(R.id.historyBTN);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        });

        user.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, User.class);
            startActivity(intent);
            finish();
        });
        history.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, History_Library.class);
            startActivity(intent);
            finish();
        });
    }

}