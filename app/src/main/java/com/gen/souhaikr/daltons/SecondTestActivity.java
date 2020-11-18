/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.os.Bundle
 *  android.support.v7.app.AppCompatActivity
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.List
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gen.souhaikr.daltons.ColorTool;
import com.gen.souhaikr.daltons.ResultActivity;
import com.gen.souhaikr.daltons.models.Question;
import java.util.List;

public class SecondTestActivity
extends AppCompatActivity {
    TextView A;
    TextView B;
    TextView C;
    TextView D;
    RelativeLayout banner;
    int count = 0;
    Question currentQ;
    int deutranopie = 0;
    String imageName;
    TextView ishiharaPlate;
    int normal = 0;
    TextView nothing;
    int protanopie = 0;
    int qid = 0;
    List<Question> quesList;
    LinearLayout quiz;
    ImageView testPlate;
    int total = 0;
    int tritaopia = 0;

    private void Next() {
        this.count = 1 + this.count;
        if (this.count < 11) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(this.count);
            this.imageName = stringBuilder.toString();
            TextView textView = this.ishiharaPlate;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ISHIHARA PLATE ");
            stringBuilder2.append(this.count);
            stringBuilder2.append("/10");
            textView.setText((CharSequence)stringBuilder2.toString());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("aaaaa");
            stringBuilder3.append(this.protanopie);
            stringBuilder3.append(this.deutranopie);
            stringBuilder3.append(this.normal);
            stringBuilder3.append(this.total);
            Log.i((String)"a", (String)stringBuilder3.toString());
            this.testPlate.setOnTouchListener(new View.OnTouchListener(){

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        int n;
                        ColorTool colorTool = new ColorTool();
                        int n2 = (int)motionEvent.getX();
                        int n3 = SecondTestActivity.this.getHotspotColor(2131165294, n2, n = (int)motionEvent.getY());
                        if (colorTool.closeMatch(-65536, n3, 25)) {
                            if (SecondTestActivity.this.count == 7 || SecondTestActivity.this.count == 1) {
                                Log.i((String)"Color", (String)"RED");
                                SecondTestActivity secondTestActivity = SecondTestActivity.this;
                                secondTestActivity.normal = 1 + secondTestActivity.normal;
                            }
                        } else if (colorTool.closeMatch(-16776961, n3, 25)) {
                            if (SecondTestActivity.this.count == 6 || SecondTestActivity.this.count == 8) {
                                Log.i((String)"Color", (String)"BLUE");
                                SecondTestActivity secondTestActivity = SecondTestActivity.this;
                                secondTestActivity.normal = 1 + secondTestActivity.normal;
                            }
                        } else if (colorTool.closeMatch(-256, n3, 25)) {
                            if (SecondTestActivity.this.count == 3) {
                                Log.i((String)"Color", (String)"YELLOW");
                                SecondTestActivity secondTestActivity = SecondTestActivity.this;
                                secondTestActivity.normal = 1 + secondTestActivity.normal;
                            }
                        } else if (colorTool.closeMatch(-16711681, n3, 25)) {
                            if (SecondTestActivity.this.count == 5) {
                                Log.i((String)"Color", (String)"CYAN");
                                SecondTestActivity secondTestActivity = SecondTestActivity.this;
                                secondTestActivity.normal = 1 + secondTestActivity.normal;
                            }
                        } else if (colorTool.closeMatch(-16777216, n3, 25)) {
                            if (SecondTestActivity.this.count == 2) {
                                Log.i((String)"Color", (String)"BLACK");
                                SecondTestActivity secondTestActivity = SecondTestActivity.this;
                                secondTestActivity.normal = 1 + secondTestActivity.normal;
                            }
                        } else if (colorTool.closeMatch(-65281, n3, 25) && SecondTestActivity.this.count == 4) {
                            Log.i((String)"Color", (String)"Magneta");
                            SecondTestActivity secondTestActivity = SecondTestActivity.this;
                            secondTestActivity.normal = 1 + secondTestActivity.normal;
                        }
                    }
                    return false;
                }
            });
            this.testPlate.setImageResource(this.getResources().getIdentifier(this.imageName, "drawable", this.getPackageName()));
            return;
        }
        if (this.normal > 4) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "normal");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
            return;
        }
        if (this.normal < 3) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "tritanopia");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
        }
    }

    private void restart() {
        this.recreate();
    }

    public int getHotspotColor(int n, int n2, int n3) {
        ImageView imageView = (ImageView)this.findViewById(n);
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap((Bitmap)imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        return bitmap.getPixel(n2, n3);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131296289);
        this.ishiharaPlate = (TextView)this.findViewById(2131165298);
        this.testPlate = (ImageView)this.findViewById(2131165331);
        this.banner = (RelativeLayout)this.findViewById(2131165227);
        this.testPlate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SecondTestActivity.this.testPlate.setBackgroundResource(17170445);
                SecondTestActivity.this.Next();
            }
        });
    }

}

