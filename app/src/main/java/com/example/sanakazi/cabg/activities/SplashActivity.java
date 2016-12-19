package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.others.Session;

public class SplashActivity extends AppCompatActivity {

    Session session;
    private static int SPLASH_TIME_OUT = 3000;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {


              /*  session = new Session(SplashActivity.this);
                if (session.isFirstTimeLaunch()) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{*/
                  //  i = new Intent(SplashActivity.this, AppIntroActivity.class);
                i = new Intent(SplashActivity.this, AppVideoActivity.class);

                    startActivity(i);
                    finish();
              //  }

            }
        }, SPLASH_TIME_OUT);

    }
}
