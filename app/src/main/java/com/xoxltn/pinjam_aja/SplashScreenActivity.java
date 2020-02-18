/*
* Copyright (c) 2020 Albert Kristaen (DBC 113 008)
* ONLY USE UNDER PERMISSION -OR- I AM GONNA CHOP YOUR HANDS OFF!
*/

package com.xoxltn.pinjam_aja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    // variabel
    Animation logoAnim, sloganAnim, ojkAnim;
    ImageView logoImage, logoOjk;
    TextView judul, slogan;

    @Override
    public void onBackPressed() {
        // kunci fungsi tombol kembali waktu splash screen
        // WHILE NO METHOD HERE, IT'S MEAN DO NOTHING B*TCH !!!
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // jalankan aplikasi dalam mode fullscreen
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // animasi splash screen
        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        sloganAnim = AnimationUtils.loadAnimation(this,R.anim.slogan_anim);
        ojkAnim = AnimationUtils.loadAnimation(this, R.anim.ojk_anim);

        // delay animasi
        sloganAnim.setStartOffset(500);
        ojkAnim.setStartOffset(700);

        // tautan animasi ke object
        logoImage = findViewById(R.id.logo_splash);
        judul = findViewById(R.id.judul);
        slogan = findViewById(R.id.slogan);
        logoOjk = findViewById(R.id.logo_ojk);

        logoImage.setAnimation(logoAnim);
        judul.setAnimation(sloganAnim);
        slogan.setAnimation(sloganAnim);
        logoOjk.setAnimation(ojkAnim);

        // splash screen
        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }

}
