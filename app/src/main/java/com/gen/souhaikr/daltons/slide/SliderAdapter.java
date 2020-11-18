/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.view.PagerAdapter
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.gen.souhaikr.daltons.slide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter
extends PagerAdapter {
    private Context context;
    public String[] description_slide = new String[]{"Keep head straight and the device in front of your eyes", "every 2 seconds you will get a new plater then you can be able to answer what did you see", "Explained result after finishing the test", "Apply Filter and see the colors in a better way"};
    public String[] heading_slide = new String[]{"TEST", "TIMER", "RESULT", "FILTER"};
    public int[] image_slide = new int[]{2131099767, 2131099822, 2131099774, 2131099769};
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void destroyItem(ViewGroup viewGroup, int n, Object object) {
        viewGroup.removeView((View)((RelativeLayout)object));
    }

    public int getCount() {
        return this.heading_slide.length;
    }

    public Object instantiateItem(ViewGroup viewGroup, int n) {
        Context context = this.context;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        View view = this.layoutInflater.inflate(2131296323, viewGroup, false);
        viewGroup.addView(view);
        ImageView imageView = (ImageView)view.findViewById(2131165293);
        TextView textView = (TextView)view.findViewById(2131165394);
        TextView textView2 = (TextView)view.findViewById(2131165393);
        imageView.setImageResource(this.image_slide[n]);
        textView.setText((CharSequence)this.heading_slide[n]);
        textView2.setText((CharSequence)this.description_slide[n]);
        return view;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

