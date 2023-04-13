package edu.huflit.learningenglishapp.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

import edu.huflit.learningenglishapp.R;
import edu.huflit.learningenglishapp.main_layout.User;

public class Login extends AppCompatActivity {

    EditText medtname, medtpass;
    Button bttlogin, bttresigter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        medtname = findViewById(R.id.EdtName);
        medtpass = findViewById(R.id.EdtPass);
        bttlogin = findViewById(R.id.BttLogin);
        bttresigter = findViewById(R.id.BttResig);
        // khai báo các button text view và button

        bttlogin.setOnClickListener(view -> {
            // Retrieve username and password from EditText fields
            String username = medtname.getText().toString();
            String password = medtname.getText().toString();

            // Check if username and password are valid
            boolean isValid = validateUser(username, password);

            // Display appropriate message and start User activity if valid
            if (isValid) {
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, User.class));
            } else {
                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
        bttresigter.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });
    }

    private boolean validateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            // Establish connection to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Learningenglishapp", "myuser", "mypassword");

            // Prepare SQL statement with parameterized query
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query and retrieve result set
            rs = stmt.executeQuery();

            // Check if result set contains any rows
            isValid = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }
}
