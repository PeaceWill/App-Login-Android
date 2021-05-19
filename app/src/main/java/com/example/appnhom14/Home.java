package com.example.appnhom14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private SQLiteConnector connector;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpView();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        getInfoUser();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private TextView lblName, lblEmail, lblPassword, valName, valEmail, valPassword, logoutButton;

    private void setUpView() {
           lblName = (TextView)findViewById(R.id.lableName);
           lblEmail = (TextView)findViewById(R.id.labelEmail);
           lblPassword = (TextView)findViewById(R.id.labelPassword);
           valName = (TextView)findViewById(R.id.valName);
           valEmail = (TextView)findViewById(R.id.valEmail);
           valPassword = (TextView)findViewById(R.id.valPassword);
           logoutButton = (TextView)findViewById(R.id.logoutButton);
    }

    private void setValueUser() {
        valName.setText(name);
        valEmail.setText(user.getEmail());
        valPassword.setText(user.getPassword());
    }

    private User user;
    private void getInfoUser() {
        connector = new SQLiteConnector(this);
        user = connector.getUser(name);
        setValueUser();
    }
}