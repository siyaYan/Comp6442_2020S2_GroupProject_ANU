package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Display Splash Screen of the App
 *
 *  @author So Young Kwon
 *  * @uid: 6511277
 */

public class Splash extends AppCompatActivity {
    ImageView splash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash = findViewById(R.id.splashImage);

        //Hides notification Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent title = new Intent(Splash.this, TitlePage.class);
                startActivity(title);
                finish();
            }
        },2000);

    }
}
