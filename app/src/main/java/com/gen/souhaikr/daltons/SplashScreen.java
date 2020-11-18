/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Handler
 *  android.support.v7.app.AppCompatActivity
 *  android.widget.ProgressBar
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Runnable
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import com.gen.souhaikr.daltons.slide.SlideActivity;

public class SplashScreen
extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ProgressBar progressBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){

            public void run() {
                Intent intent = new Intent((Context)SplashScreen.this, SlideActivity.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        }, 2000L);
    }

}

