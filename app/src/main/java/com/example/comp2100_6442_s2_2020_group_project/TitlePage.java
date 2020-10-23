package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitlePage extends AppCompatActivity {
    Button login;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        login = findViewById(R.id.mainsignin);
        start = findViewById(R.id.toMain);
    }
    public void logIn(View v){
        Intent login = new Intent(TitlePage.this, UserLogin.class);
        startActivity(login);
    }

    public void start(View v){
        Intent main = new Intent(TitlePage.this, MainActivity.class);
        startActivity(main);

    }
}
