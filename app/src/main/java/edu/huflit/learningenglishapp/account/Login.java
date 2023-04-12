package edu.huflit.learningenglishapp.account;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.huflit.learningenglishapp.R;

public class Login extends AppCompatActivity {

    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medtname = this.findViewById(R.id.EdtName);
        medtpass = this.findViewById(R.id.EdtPass);
        bttlogin = this.findViewById(R.id.BttLogin);
        bttresigter = this.findViewById(R.id.BttResig);
    }
}
