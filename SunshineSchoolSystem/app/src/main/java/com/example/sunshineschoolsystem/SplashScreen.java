    package com.example.sunshineschoolsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

    public class SplashScreen extends AppCompatActivity {
        private static final int SPLASH_DELAY_MS = 3000; // 3 seconds
        LottieAnimationView animation_view;
        Animation fade_in;
        TextView tvGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        animation_view = findViewById(R.id.animation_view);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        tvGetStarted = findViewById(R.id.tvGetStarted);
        tvGetStarted.setAnimation(fade_in);
        tvGetStarted.setAnimation(fade_in);






            // Use a Handler to delay the transition to the main activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    animation_view.playAnimation();
                    // Start the main activity
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close the splash screen activity
                }
            }, SPLASH_DELAY_MS);
    }
}