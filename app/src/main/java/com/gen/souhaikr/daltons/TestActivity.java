/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.support.v7.app.AppCompatActivity
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.List
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gen.souhaikr.daltons.ResultActivity;
import com.gen.souhaikr.daltons.models.Question;
import com.gen.souhaikr.daltons.utils.dbHelper;
import java.util.List;

public class TestActivity
extends AppCompatActivity
implements View.OnClickListener {
    TextView A;
    TextView A2;
    TextView B;
    TextView B2;
    TextView C;
    TextView C2;
    TextView D;
    TextView D2;
    int count = 1;
    Question currentQ;
    int deutranopie = 0;
    String imageName;
    TextView ishiharaPlate;
    int normal = 0;
    TextView nothing;
    TextView nothing2;
    int protanopie = 0;
    int qid = 0;
    List<Question> quesList;
    LinearLayout quiz;
    LinearLayout quiz2;
    ImageView testPlate;
    int total = 0;

    private void Next() {
        this.A.setBackgroundColor(this.getResources().getColor(2130968652));
        this.B.setBackgroundColor(this.getResources().getColor(2130968652));
        this.C.setBackgroundColor(this.getResources().getColor(2130968652));
        this.D.setBackgroundColor(this.getResources().getColor(2130968652));
        this.A2.setBackgroundColor(this.getResources().getColor(2130968652));
        this.B2.setBackgroundColor(this.getResources().getColor(2130968652));
        this.C2.setBackgroundColor(this.getResources().getColor(2130968652));
        this.D2.setBackgroundColor(this.getResources().getColor(2130968652));
        this.count = 1 + this.count;
        if (this.count < 11) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("plate");
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
            this.testPlate.setImageResource(this.getResources().getIdentifier(this.imageName, "drawable", this.getPackageName()));
            this.quiz.setVisibility(8);
            this.quiz2.setVisibility(8);
            this.testPlate.setVisibility(0);
            this.ishiharaPlate.setVisibility(0);
            new Handler().postDelayed(new Runnable(){

                public void run() {
                    TestActivity.this.testPlate.setVisibility(8);
                    TestActivity.this.ishiharaPlate.setVisibility(8);
                    TestActivity.this.currentQ = (Question)TestActivity.this.quesList.get(TestActivity.this.qid);
                    if (TestActivity.this.count > 8) {
                        TestActivity.this.quiz2.setVisibility(0);
                        TestActivity.this.setQuestionView2();
                        return;
                    }
                    TestActivity.this.setQuestionView();
                    TestActivity.this.quiz.setVisibility(0);
                }
            }, 2000L);
            return;
        }
        if (this.normal > 7) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "normal");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
            return;
        }
        if (this.protanopie > 4) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "protanopia");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
            return;
        }
        if (this.deutranopie > 4) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "deuteranopia");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
            return;
        }
        if (this.total > 7) {
            Intent intent = new Intent((Context)this, ResultActivity.class);
            intent.putExtra("result", "total");
            this.startActivity(intent);
            this.overridePendingTransition(2130771980, 2130771981);
            return;
        }
        Intent intent = new Intent((Context)this, ResultActivity.class);
        intent.putExtra("result", "total");
        this.startActivity(intent);
        this.overridePendingTransition(2130771980, 2130771981);
    }

    private void restart() {
        this.recreate();
    }

    private void setQuestionView() {
        this.A.setText((CharSequence)this.currentQ.getOPTA());
        this.B.setText((CharSequence)this.currentQ.getOPTB());
        this.C.setText((CharSequence)this.currentQ.getOPTC());
        this.D.setText((CharSequence)this.currentQ.getOPTD());
        this.qid = 1 + this.qid;
    }

    private void setQuestionView2() {
        this.A2.setText((CharSequence)this.currentQ.getOPTA());
        this.B2.setText((CharSequence)this.currentQ.getOPTB());
        this.C2.setText((CharSequence)this.currentQ.getOPTC());
        this.D2.setText((CharSequence)this.currentQ.getOPTD());
        this.qid = 1 + this.qid;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            default: {
                return;
            }
            case 2131165320: {
                this.total = 1 + this.total;
                this.Next();
                return;
            }
            case 2131165319: {
                this.total = 1 + this.total;
                this.Next();
                return;
            }
            case 2131165193: {
                this.D2.setBackgroundColor(-65281);
                if (this.D.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.D.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.D.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165192: {
                this.D.setBackgroundColor(-65281);
                if (this.D.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.D.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.D.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165190: {
                this.C2.setBackgroundColor(-65281);
                if (this.C.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.C.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.C.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165189: {
                this.C.setBackgroundColor(-65281);
                if (this.C.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.C.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.C.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165188: {
                this.B2.setBackgroundColor(-65281);
                if (this.B.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.B.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.B.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165187: {
                this.B.setBackgroundColor(-65281);
                if (this.B.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.B.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.B.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165185: {
                this.A2.setBackgroundColor(-65281);
                if (this.A.getText().equals((Object)this.currentQ.getANSWER())) {
                    this.normal = 1 + this.normal;
                } else if (this.A.getText().equals((Object)this.currentQ.getPr())) {
                    this.protanopie = 1 + this.protanopie;
                } else if (this.A.getText().equals((Object)this.currentQ.getDe())) {
                    this.deutranopie = 1 + this.deutranopie;
                }
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        TestActivity.this.Next();
                    }
                }, 300L);
                return;
            }
            case 2131165184: 
        }
        this.A.setBackgroundColor(-65281);
        if (this.A.getText().equals((Object)this.currentQ.getANSWER())) {
            this.normal = 1 + this.normal;
        } else if (this.A.getText().equals((Object)this.currentQ.getPr())) {
            this.protanopie = 1 + this.protanopie;
        } else if (this.A.getText().equals((Object)this.currentQ.getDe())) {
            this.deutranopie = 1 + this.deutranopie;
        }
        new Handler().postDelayed(new Runnable(){

            public void run() {
                TestActivity.this.Next();
            }
        }, 300L);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131296292);
        this.quesList = new dbHelper((Context)this).getAllQuestions();
        this.currentQ = (Question)this.quesList.get(this.qid);
        this.ishiharaPlate = (TextView)this.findViewById(2131165298);
        this.testPlate = (ImageView)this.findViewById(2131165331);
        this.quiz = (LinearLayout)this.findViewById(2131165335);
        this.quiz2 = (LinearLayout)this.findViewById(2131165336);
        this.A = (TextView)this.findViewById(2131165184);
        this.A.setOnClickListener((View.OnClickListener)this);
        this.B = (TextView)this.findViewById(2131165187);
        this.B.setOnClickListener((View.OnClickListener)this);
        this.C = (TextView)this.findViewById(2131165189);
        this.C.setOnClickListener((View.OnClickListener)this);
        this.D = (TextView)this.findViewById(2131165192);
        this.D.setOnClickListener((View.OnClickListener)this);
        this.nothing = (TextView)this.findViewById(2131165319);
        this.nothing.setOnClickListener((View.OnClickListener)this);
        this.A2 = (TextView)this.findViewById(2131165185);
        this.A2.setOnClickListener((View.OnClickListener)this);
        this.B2 = (TextView)this.findViewById(2131165188);
        this.B2.setOnClickListener((View.OnClickListener)this);
        this.C2 = (TextView)this.findViewById(2131165190);
        this.C2.setOnClickListener((View.OnClickListener)this);
        this.D2 = (TextView)this.findViewById(2131165193);
        this.D2.setOnClickListener((View.OnClickListener)this);
        this.nothing2 = (TextView)this.findViewById(2131165320);
        this.nothing2.setOnClickListener((View.OnClickListener)this);
        new Handler().postDelayed(new Runnable(){

            public void run() {
                TestActivity.this.testPlate.setVisibility(8);
                TestActivity.this.ishiharaPlate.setVisibility(8);
                TestActivity.this.currentQ = (Question)TestActivity.this.quesList.get(TestActivity.this.qid);
                if (TestActivity.this.count > 8) {
                    TestActivity.this.quiz2.setVisibility(0);
                    TestActivity.this.setQuestionView2();
                    return;
                }
                TestActivity.this.setQuestionView();
                TestActivity.this.quiz.setVisibility(0);
            }
        }, 2000L);
    }

}

