package com.example.appnhom14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText fName, fPassword;
    private TextView logButton, regButton;
    private SQLiteConnector connector;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        // REGISTER
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        connector = new SQLiteConnector(this);

        // LOG IN
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SomeFunc hash = new SomeFunc();
                String hashpw = hash.hashSHA256(fPassword.getText().toString());
                user = new User(fName.getText().toString(), hashpw);
                if (validateInput()) {
                    if (connector.checkUser(user.getName(), user.getPassword())) {
                        finish();
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        intent.putExtra("name", user.getName());
                        startActivity(intent);
                    } else {
                        Log.d("user", user.getEmail() + " " + user.getPassword());
                        showMessage("Name or Password is not correct");
                        resetValue();
                    }
                }
            }
        });
    }


    private void setUpView() {
        fName = (EditText)findViewById(R.id.loginName);
        fPassword = (EditText)findViewById(R.id.loginPassword);
        logButton = (TextView)findViewById(R.id.logButton);
        regButton = (TextView)findViewById(R.id.regButton);
        fName.setText("");
        fPassword.setText("");
    }

    private Boolean validateInput() {
        if (fName.getText().toString().equals("") || fPassword.getText().toString().equals("")){
            showMessage("Please fill in Name and Password");
            return false;
        }
        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void resetValue() {
        fName.findFocus();
        fName.setText("");
        fPassword.setText("");
    }
}