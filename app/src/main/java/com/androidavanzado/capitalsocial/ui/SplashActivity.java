package com.androidavanzado.capitalsocial.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.androidavanzado.capitalsocial.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private SplashActivity mContext;

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //quitamos la barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mContext = this;

        timerActivity();
    }

    public void timerActivity(){
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                initPreferences();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void initPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String user = sharedPreferences.getString("User", "error");
        if(user.equals("error")) {
            // Start the next activity
            Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);

            // Close the activity so the user won't able to go back this
            // activity pressing Back button
            finish();
        } else {
            // Start the next activity
            Intent mainIntent = new Intent().setClass(SplashActivity.this, PromotionsActivity.class);
            startActivity(mainIntent);

            // Close the activity so the user won't able to go back this
            // activity pressing Back button
            finish();
        }
    }
}
