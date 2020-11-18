/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.support.v7.app.ActionBar
 *  android.support.v7.app.AppCompatActivity
 *  android.support.v7.widget.Toolbar
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.facebook.login.LoginManager
 *  com.squareup.picasso.Picasso
 *  com.squareup.picasso.RequestCreator
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.net.MalformedURLException
 *  java.net.URL
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.login.LoginManager;
import com.gen.souhaikr.daltons.CameraActivity;
import com.gen.souhaikr.daltons.MainActivity;
import com.gen.souhaikr.daltons.TestActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity
extends AppCompatActivity {
    TextView firstnameTxt;
    ImageView imageView;
    ImageView startTest;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_home );
        Toolbar toolbar = (Toolbar)this.findViewById(2131165389);
        this.setSupportActionBar(toolbar);
        if (toolbar != null) {
            this.getSupportActionBar().setTitle((CharSequence)"");
        }
        this.imageView = (ImageView)this.findViewById(2131165292);
        this.startTest = (ImageView)this.findViewById(2131165373);
        String string2 = this.getIntent().getExtras().getString("first_name");
        String string3 = this.getIntent().getExtras().getString("user_id");
        TextView textView = this.firstnameTxt = (TextView)this.findViewById(2131165280);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ");
        stringBuilder.append(string2);
        stringBuilder.append(" !");
        textView.setText((CharSequence)stringBuilder.toString());
        try {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://graph.facebook.com/");
            stringBuilder2.append(string3);
            stringBuilder2.append("/picture?type=large");
            URL uRL = new URL(stringBuilder2.toString());
            Picasso.get().load(String.valueOf((Object)uRL)).into(this.imageView);
        }
        catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        this.imageView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this.getApplicationContext(), CameraActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });
        this.startTest.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this.getApplicationContext(), TestActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        this.getMenuInflater().inflate(2131361792, menu2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131165215) {
            LoginManager.getInstance().logOut();
            this.startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
        }
        return true;
    }

}

