/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.ArgbEvaluator
 *  android.animation.ObjectAnimator
 *  android.animation.TypeEvaluator
 *  android.animation.ValueAnimator
 *  android.annotation.SuppressLint
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.Paint
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ImageButton
 *  android.widget.NumberPicker
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.Switch
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 */
package com.gen.souhaikr.daltons.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.gen.souhaikr.daltons.FilterActivity;
import java.lang.reflect.Field;

@SuppressLint(value={"InflateParams"})
public class UIComponents {
    private final FilterActivity context;
    private final String[] names;
    private final int numFilters;

    public UIComponents(FilterActivity filterActivity, int n, String[] arrstring) {
        this.context = filterActivity;
        this.numFilters = n;
        this.names = arrstring;
    }

    private void animateClickedButton(ImageButton imageButton) {
        ObjectAnimator.ofFloat((Object)imageButton, (String)"alpha", (float[])new float[]{1.0f, 0.5f, 1.0f}).start();
    }

    private AlertDialog cancelableDialog(AlertDialog.Builder builder) {
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    private void initializeNumberPickerWithFilters(NumberPicker numberPicker) {
        numberPicker.setMinValue(3);
        numberPicker.setMaxValue(-1 + this.numFilters);
        numberPicker.setDisplayedValues(this.names);
    }

    private static void setNumberPickerTextColor(NumberPicker numberPicker, int n) {
        int n2 = numberPicker.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view = numberPicker.getChildAt(n3);
            if (view instanceof EditText) {
                try {
                    Field field = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    field.setAccessible(true);
                    ((Paint)field.get((Object)numberPicker)).setColor(n);
                    ((EditText)view).setTextColor(n);
                    numberPicker.invalidate();
                    return;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    continue;
                }
            }
            ++n3;
        }
    }

    private void textViewSeekBarLinker(View view, int n, int n2, int n3, int n4, final int[] arrn, final int n5, final int n6, final int n7) {
        final TextView textView = (TextView)view.findViewById(n);
        textView.setText(arrn[n5]);
        final SeekBar seekBar = (SeekBar)view.findViewById(n2);
        seekBar.setMax(n6);
        seekBar.setProgress(arrn[n5]);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                UIComponents.this.setViewValsTextArray(textView, arrn, n5, seekBar.getProgress() * n7);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                UIComponents.this.setViewValsTextArray(textView, arrn, n5, seekBar.getProgress() * n7);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                UIComponents.this.setViewValsTextArray(textView, arrn, n5, seekBar.getProgress() * n7);
            }
        };
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        Button button = (Button)view.findViewById(n3);
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                int[] arrn2 = arrn;
                int n = n5;
                arrn2[n] = arrn2[n] - n7;
                if (arrn[n5] <= 0) {
                    arrn[n5] = 0;
                }
                UIComponents.this.setViewValsSeekText(textView, seekBar, arrn[n5], n7);
            }
        };
        button.setOnClickListener(onClickListener);
        Button button2 = (Button)view.findViewById(n4);
        View.OnClickListener onClickListener2 = new View.OnClickListener(){

            public void onClick(View view) {
                int[] arrn2 = arrn;
                int n = n5;
                arrn2[n] = arrn2[n] + n7;
                if (arrn[n5] >= n6 * n7) {
                    arrn[n5] = n6 * n7;
                }
                UIComponents.this.setViewValsSeekText(textView, seekBar, arrn[n5], n7);
            }
        };
        button2.setOnClickListener(onClickListener2);
    }

    public void initialUISetup(final int[] arrn) {
        final NumberPicker numberPicker = (NumberPicker)this.context.findViewById(2131165186);
        UIComponents.setNumberPickerTextColor(numberPicker, -65536);
        ImageButton imageButton = (ImageButton)this.context.findViewById(2131165185);
        imageButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                arrn[0] = numberPicker.getValue();
                arrn[1] = numberPicker.getValue();
                UIComponents.this.context.setUpCameraViews();
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt((Object)imageButton, (String)"alpha", (int[])new int[]{100, 255});
        objectAnimator.setDuration(1000L);
        objectAnimator.setEvaluator((TypeEvaluator)new ArgbEvaluator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.start();
        ImageButton imageButton2 = (ImageButton)this.context.findViewById(2131165187);
        imageButton2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                UIComponents.this.animateClickedButton((ImageButton)UIComponents.this.context.findViewById(2131165187));
            }
        });
        imageButton2.setAlpha(1.0f);
        ImageButton imageButton3 = (ImageButton)this.context.findViewById(2131165184);
        imageButton3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                UIComponents.this.animateClickedButton((ImageButton)UIComponents.this.context.findViewById(2131165184));
            }
        });
        imageButton3.setAlpha(1.0f);
    }

    public AlertDialog retrieveHelpDialog() {
        return this.cancelableDialog(new AlertDialog.Builder((Context)this.context).setTitle((CharSequence)"Help").setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new C00694()).setMessage((CharSequence)"Reality Hacker Version 0.9\n\nSelect a filter, then click the VisoR button to view your world in a new way with a VR headset.\n\nSwipe on the camera screens or use the volume button to change the filter while viewing.\n\nUse a fisheye lens to increase your field of view.\n\nCompatible with Google Cardboard, Durovis Dive, and other VR Headsets.\n\n\u00a9 2014 VisoR"));
    }

    public AlertDialog retrieveSettingsDialog(final int[] arrn) {
        View view = this.context.getLayoutInflater().inflate(2130903041, null);
        final Switch switch_ = (Switch)view.findViewById(2131165188);
        int n = arrn[0];
        boolean bl = false;
        if (n == 1) {
            bl = true;
        }
        switch_.setChecked(bl);
        this.textViewSeekBarLinker(view, 2131165191, 2131165193, 2131165190, 2131165192, arrn, 1, 24, 15);
        this.textViewSeekBarLinker(view, 2131165196, 2131165198, 2131165195, 2131165197, arrn, 2, 24, 15);
        this.textViewSeekBarLinker(view, 2131165201, 2131165203, 2131165200, 2131165202, arrn, 3, 24, 15);
        return this.cancelableDialog(new AlertDialog.Builder((Context)this.context).setTitle((CharSequence)"Settings").setPositiveButton((CharSequence)"Ok", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (switch_.isChecked()) {
                    arrn[0] = 1;
                    return;
                }
                arrn[0] = 0;
            }
        }).setView(view));
    }

    protected void setViewValsSeekText(TextView textView, SeekBar seekBar, int n, int n2) {
        textView.setText(n);
        seekBar.setProgress(n / n2);
    }

    protected void setViewValsTextArray(TextView textView, int[] arrn, int n, int n2) {
        textView.setText(n2);
        arrn[n] = n2;
    }

    class C00694
    implements DialogInterface.OnClickListener {
        C00694() {
        }

        public void onClick(DialogInterface dialogInterface, int n) {
        }
    }

    class C00716
    implements DialogInterface.OnClickListener {
        C00716() {
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            UIComponents.this.context.endCurrentStreaming();
        }
    }

}

