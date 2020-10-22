package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String courseID = intent.getExtras().getString("courseID");

        String website = "https://programsandcourses.anu.edu.au/course/" + courseID;

        webview = findViewById(R.id.course_webview);
        webview.loadUrl(website);
    }
}