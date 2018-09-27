package com.example.arifinfrds.logineventhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /* Mengikat View pada xml dengan Java */

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);


        /* Memasangkan OnClickListener ke loginButton */

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isInputEmpty(email) || isInputEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Input tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isEmailValid(email)) {
                    Toast.makeText(LoginActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isPasswordValid(password)) {
                    Toast.makeText(LoginActivity.this, "Password tidak boleh < 6", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
            }
        });


        /* Memasangkan OnLongClickListener ke loginButton */

        loginButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String email = emailEditText.getText().toString();
                if (isInputEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Input tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!isEmailValid(email)) {
                    Toast.makeText(LoginActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(LoginActivity.this, "Halo " + email + ", Anda masuk sebagai admin. ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    // MARK: - Helper

    private Boolean isInputEmpty(String input) {
        if (TextUtils.isEmpty(input)) {
            return true;
        }
        return false;
    }

    private static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        if (password.toCharArray().length < 6) {
            return false;
        }
        return true;
    }


}
