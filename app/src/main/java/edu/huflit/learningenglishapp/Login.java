
package edu.huflit.learningenglishapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttlogin = findViewById(R.id.BttLogin);
        bttresigter = findViewById(R.id.BttResig);
    }
}
