/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.support.v7.app.AppCompatActivity
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gen.souhaikr.daltons.FilterActivity;
import com.gen.souhaikr.daltons.SecondTestActivity;
import com.gen.souhaikr.daltons.TestActivity;

public class ResultActivity
extends AppCompatActivity {
    Button btn;
    Button btnRestart;
    String data;
    TextView txt;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131296288);
        this.data = this.getIntent().getExtras().getString("result", "defaultKey");
        this.txt = (TextView)this.findViewById(2131165338);
        if (this.data.equals((Object)"tritanopia")) {
            this.txt.setText((CharSequence)"Tritaopia-Blue-Yellow Color Blindness");
        } else if (this.data.equals((Object)"protanopia")) {
            this.txt.setText((CharSequence)"Protanopia-Red-Green Color Blindness");
        } else if (this.data.equals((Object)"deuteranopia")) {
            this.txt.setText((CharSequence)"Deuteranopia-Red-Green Color Blindness");
        } else if (this.data.equals((Object)"total")) {
            this.txt.setText((CharSequence)"Vision Problem");
        } else {
            this.txt.setText((CharSequence)"Normal Vision");
        }
        this.btn = (Button)this.findViewById(2131165237);
        this.btnRestart = (Button)this.findViewById(2131165238);
        this.btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (!ResultActivity.this.data.equals((Object)"total") && !ResultActivity.this.data.equals((Object)"normal")) {
                    Intent intent = new Intent(ResultActivity.this.getApplicationContext(), FilterActivity.class);
                    if (ResultActivity.this.data.equals((Object)"protanopia")) {
                        intent.putExtra("filter", "protanopia");
                    } else if (ResultActivity.this.data.equals((Object)"deuteranopia")) {
                        intent.putExtra("filter", "deuteranopia");
                    } else if (ResultActivity.this.data.equals((Object)"tritanopia")) {
                        intent.putExtra("filter", "tritanopia");
                    }
                    ResultActivity.this.startActivity(intent);
                    ResultActivity.this.overridePendingTransition(2130771980, 2130771981);
                    return;
                }
                Intent intent = new Intent(ResultActivity.this.getApplicationContext(), SecondTestActivity.class);
                ResultActivity.this.startActivity(intent);
                ResultActivity.this.overridePendingTransition(2130771980, 2130771981);
            }
        });
        this.btnRestart.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this.getApplicationContext(), TestActivity.class);
                ResultActivity.this.startActivity(intent);
                ResultActivity.this.overridePendingTransition(2130771980, 2130771981);
            }
        });
    }

}

