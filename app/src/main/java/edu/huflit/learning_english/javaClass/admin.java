package edu.huflit.learning_english.javaClass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.huflit.learning_english.R;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Tìm kiếm các phần tử giao diện người dùng bằng ID
        TextView TwTitle = findViewById(R.id.TwTitle);
        ImageView img1 = findViewById(R.id.img1);
        TextView txt1_1 = findViewById(R.id.txt1_1);
        TextView txt1_2 = findViewById(R.id.txt1_2);
        Button btnAccess1 = findViewById(R.id.btnAccess1);
        ImageView img2 = findViewById(R.id.img2);
        TextView txt2 = findViewById(R.id.txt2);
        Button btnAccess2 = findViewById(R.id.btnAccess2);
        ImageView img3 = findViewById(R.id.img3);
        TextView txt3 = findViewById(R.id.txt3);
        Button btnAccess3 = findViewById(R.id.btnAccess3);
    }
}