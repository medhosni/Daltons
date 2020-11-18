/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.support.v7.app.AppCompatActivity
 *  android.text.Html
 *  android.view.View
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.gen.souhaikr.daltons.slide;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gen.souhaikr.daltons.TestActivity;
import com.gen.souhaikr.daltons.slide.SliderAdapter;

public class SlideActivity
extends AppCompatActivity {
    private Button btnNext;
    private Button btnSkip;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private ViewPager sViewPager;
    SliderAdapter sliderAdapter;
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){

        public void onPageScrollStateChanged(int n) {
        }

        public void onPageScrolled(int n, float f, int n2) {
        }

        public void onPageSelected(int n) {
            SlideActivity.this.addBottomDots(n);
            if (n == -1 + SlideActivity.this.sliderAdapter.image_slide.length) {
                SlideActivity.this.btnNext.setText((CharSequence)SlideActivity.this.getString(2131492933));
                SlideActivity.this.btnSkip.setVisibility(8);
                return;
            }
            SlideActivity.this.btnNext.setText((CharSequence)SlideActivity.this.getString(2131492930));
            SlideActivity.this.btnSkip.setVisibility(0);
        }
    };

    private void addBottomDots(int n) {
        this.dots = new TextView[4];
        this.dotsLayout.removeAllViews();
        for (int i = 0; i < this.dots.length; ++i) {
            this.dots[i] = new TextView((Context)this);
            this.dots[i].setText((CharSequence)Html.fromHtml((String)"&#8226;"));
            this.dots[i].setTextSize(35.0f);
            this.dots[i].setTextColor(this.getResources().getColor(2130968622));
            this.dotsLayout.addView((View)this.dots[i]);
        }
        if (this.dots.length > 0) {
            this.dots[n].setTextColor(this.getResources().getColor(2130968670));
        }
    }

    private int getItem(int n) {
        return n + this.sViewPager.getCurrentItem();
    }

    private void initView() {
        this.sViewPager = (ViewPager)this.findViewById(2131165342);
        this.dotsLayout = (LinearLayout)this.findViewById(2131165301);
        this.btnSkip = (Button)this.findViewById(2131165235);
        this.btnNext = (Button)this.findViewById(2131165234);
    }

    private void launchHomeScreen() {
        this.startActivity(new Intent((Context)this, TestActivity.class));
        this.finish();
    }

    public void addDotIndicator() {
        this.dots = new TextView[3];
        for (int i = 0; i < this.dots.length; ++i) {
            this.dots[i] = new TextView((Context)this);
            this.dots[i].setText((CharSequence)Html.fromHtml((String)"&#8266;"));
            this.dots[i].setTextSize(35.0f);
            this.dots[i].setTextColor(this.getResources().getColor(2130968622));
            this.dotsLayout.addView((View)this.dots[i]);
        }
    }

    public void btnNextClick(View view) {
        int n = this.getItem(1);
        if (n < this.sliderAdapter.image_slide.length) {
            this.sViewPager.setCurrentItem(n);
            return;
        }
        this.launchHomeScreen();
    }

    public void btnSkipClick(View view) {
        this.launchHomeScreen();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131296290);
        this.initView();
        this.sliderAdapter = new SliderAdapter((Context)this);
        this.sViewPager.setAdapter((PagerAdapter)this.sliderAdapter);
        this.sViewPager.addOnPageChangeListener(this.viewPagerPageChangeListener);
        this.addBottomDots(0);
    }

}

