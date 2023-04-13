package edu.huflit.learningenglishapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;

import Database.User;
import edu.huflit.learningenglishapp.account.Login;
import edu.huflit.learningenglishapp.main_layout.History_Library;
import edu.huflit.learningenglishapp.main_layout.Review;


public class MainActivity extends AppCompatActivity{

    ImageButton back, user,history, review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_test);

        back = (ImageButton) findViewById(R.id.backBTN);
        user = (ImageButton) findViewById(R.id.user);
        history = (ImageButton) findViewById(R.id.historyBTN);
        review  = (ImageButton) findViewById(R.id.reviewBTN);

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
        review.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Review.class);
            startActivity(intent);
            finish();
        });

    }

}