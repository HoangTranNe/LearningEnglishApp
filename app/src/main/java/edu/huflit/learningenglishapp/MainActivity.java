package edu.huflit.learningenglishapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import Database.User;
import edu.huflit.learningenglishapp.account.*;
import edu.huflit.learningenglishapp.main_layout.Blog;
import edu.huflit.learningenglishapp.main_layout.History_Library;
import edu.huflit.learningenglishapp.main_layout.Review;


public class MainActivity extends AppCompatActivity{

    private EditText inpBlog;
    private Button postBlog;
    private RecyclerView showBlog;
    private Blog.BlogAdapter blogAdapter;
    private List<Blog> blogList = new ArrayList<>();

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
        inpBlog = findViewById(R.id.inpBlog);
        postBlog = findViewById(R.id.postBlog);
        showBlog = findViewById(R.id.showBlog);


        blogAdapter = new Blog.BlogAdapter(blogList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        showBlog.setLayoutManager(layoutManager);
        showBlog.setAdapter(blogAdapter);

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

        postBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Title";
                String content = inpBlog.getText().toString();

                Blog blog = new Blog(title, content);
                blogList.add(blog);
                blogAdapter.notifyDataSetChanged();
                inpBlog.setText("");
            }
        });


    }

}