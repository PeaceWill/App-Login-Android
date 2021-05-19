package com.example.appnhom14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private SQLiteConnector connector;
    private User user;
    private String name, email, password , repassword, hashpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpView();
        connector = new SQLiteConnector(this);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue();
                if (validate()){
                    SomeFunc hash = new SomeFunc();
                    hashpw = hash.hashSHA256(password);
                    user = new User(name, email, hashpw);
                    if (!connector.checkUser(email)){
                        connector.addUser(user);
                        finish();
                        showMessage("Sign up account successfully");
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        showMessage("Email has been already existed");
                    }
                }
            }
        });
    }

    private EditText regName, regEmail, regPassword, regRePassword;
    private TextView createButton;

    private void setUpView() {
        regName = (EditText)findViewById(R.id.regName);
        regEmail = (EditText)findViewById(R.id.regEmail);
        regPassword = (EditText)findViewById(R.id.regPassword);
        regRePassword = (EditText)findViewById(R.id.regRePassword);
        createButton = (TextView)findViewById(R.id.crateAccButton);
    }

    private void setValue() {
        name = regName.getText().toString();
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();
        repassword = regRePassword.getText().toString();
    }

    private Boolean validate() {
        if (name.equals("") || email.equals("") || password.equals("")){
            showMessage("Please fill full of these fields");
            return false;
        }
        if (!password.equals(repassword)){
            showMessage("Password is not similar");
            return false;
        }
        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}