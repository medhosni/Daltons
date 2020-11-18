/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Point
 *  android.graphics.SurfaceTexture
 *  android.graphics.SurfaceTexture$OnFrameAvailableListener
 *  android.os.Bundle
 *  android.support.v7.app.AlertDialog
 *  android.view.Display
 *  android.view.GestureDetector
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.WindowManager
 *  android.widget.ImageButton
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashSet
 *  java.util.Set
 */
package com.gen.souhaikr.daltons;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import com.gen.souhaikr.daltons.utils.CameraAdapter;
import com.gen.souhaikr.daltons.utils.FilterVault;
import com.gen.souhaikr.daltons.utils.MyGLSurfaceView;
import com.gen.souhaikr.daltons.utils.MyRenderer;
import com.gen.souhaikr.daltons.utils.OnSwipeTouchListener;
import com.gen.souhaikr.daltons.utils.UIComponents;
import java.util.HashSet;
import java.util.Set;

public class FilterActivity
extends Activity
implements SurfaceTexture.OnFrameAvailableListener {
    private static final int BOTH_FILTERS = 2;
    private static final int LEFT_FILTER = 1;
    public static int NO_FILTERS = 0;
    private static final int RIGHT_FILTER = 3;
    private static final float[] defaultUniformValues;
    private static final int[] filterIndices;
    private static final int[] settingsOptions;
    private final Set<Toast> bread = new HashSet();
    private UIComponents builder;
    private final CameraAdapter cameraAdapter = new CameraAdapter();
    String data;
    int filterNumber;
    private MyGLSurfaceView glSurfaceView;
    private AlertDialog helpDialog;
    private boolean isCurrentlyStreaming = false;
    private View myView;
    private final String[] names = FilterVault.getAllNames();
    private final int numFilters = FilterVault.getNumFilters();
    private MyRenderer renderer;
    private AlertDialog settDialog;
    private final Point size = new Point();
    private SurfaceTexture surface;
    private ViewGroup vgParent;

    static {
        NO_FILTERS = -1;
        defaultUniformValues = new float[]{10.0f, 10.0f};
        filterIndices = new int[2];
        int[] arrn = new int[4];
        arrn[0] = 1;
        settingsOptions = arrn;
    }

    private Toast createFilterToast(int n, int n2) {
        Toast toast = Toast.makeText((Context)this.getApplicationContext(), (CharSequence)this.names[filterIndices[n]], (int)0);
        toast.setGravity(17, n2, 0);
        toast.show();
        return toast;
    }

    private void fixFilterGivenIndex(int n) {
        if (filterIndices[n] < 0) {
            int[] arrn = filterIndices;
            arrn[n] = arrn[n] + this.numFilters;
        }
        FilterActivity.filterIndices[n] = filterIndices[n] % this.numFilters;
    }

    @SuppressLint(value={"WrongConstant"})
    private void fixScreenView() {
        this.setRequestedOrientation(0);
        this.requestWindowFeature(1);
        this.getWindow().addFlags(128);
        this.getWindow().setFlags(1024, 1024);
    }

    private boolean processAndroidButton(int n, KeyEvent keyEvent) {
        if (settingsOptions[0] == 1 && this.isCurrentlyStreaming) {
            if (n == 25) {
                int[] arrn = filterIndices;
                arrn[0] = 1 + arrn[0];
                FilterActivity.filterIndices[1] = filterIndices[0];
                this.updateFilters(2);
                return true;
            }
            if (n == 24) {
                int[] arrn = filterIndices;
                arrn[0] = arrn[0] - 1;
                FilterActivity.filterIndices[1] = filterIndices[0];
                this.updateFilters(2);
                return true;
            }
            if (n == 82) {
                return true;
            }
            if (n == 4) {
                this.endCurrentStreaming();
                return false;
            }
        }
        return super.onKeyDown(n, keyEvent);
    }

    @SuppressLint(value={"ResourceType"})
    private void replaceCurrentView(View view) {
        this.myView = this.getWindow().getDecorView().findViewById(16908290);
        this.vgParent = (ViewGroup)this.myView.getParent();
        this.vgParent.removeView(this.myView);
        this.vgParent.addView(view);
    }

    public void endCurrentStreaming() {
        this.cameraAdapter.destroyCamera();
        this.vgParent.removeView((View)this.glSurfaceView);
        this.isCurrentlyStreaming = false;
        this.vgParent.addView(this.myView);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.data = this.getIntent().getExtras().getString("filter", "defaultKey");
        if (this.data.equals((Object)"protanopia")) {
            this.filterNumber = 2;
        } else if (this.data.equals((Object)"deuteranopia")) {
            this.filterNumber = 4;
        } else if (this.data.equals((Object)"tritanopia")) {
            this.filterNumber = 6;
        }
        this.fixScreenView();
        this.setContentView(R.layout.activity_filter);
        this.getWindowManager().getDefaultDisplay().getSize(this.size);
        this.builder = new UIComponents(this, this.numFilters, this.names);
        float[] arrf = defaultUniformValues;
        double d = this.size.x;
        Double.isNaN((double)d);
        arrf[0] = (float)(2.0 / d);
        float[] arrf2 = defaultUniformValues;
        double d2 = this.size.y;
        Double.isNaN((double)d2);
        arrf2[1] = (float)(1.0 / d2);
        ((ImageButton)this.findViewById(R.id.imgbtn)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FilterActivity.access$000()[0] = FilterActivity.this.filterNumber;
                FilterActivity.access$000()[1] = FilterActivity.this.filterNumber;
                FilterActivity.this.setUpCameraViews();
            }
        });
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.glSurfaceView.requestRender();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        super.onKeyDown(n, keyEvent);
        return this.processAndroidButton(n, keyEvent);
    }

    public boolean onKeyLongPress(int n, KeyEvent keyEvent) {
        super.onKeyLongPress(n, keyEvent);
        return this.processAndroidButton(n, keyEvent);
    }

    public void onPause() {
        super.onPause();
        this.cameraAdapter.destroyCamera();
    }

    public void onResume() {
        super.onResume();
        this.cameraAdapter.destroyCamera();
    }

    public void onStop() {
        super.onStop();
        if (this.isCurrentlyStreaming) {
            System.exit((int)0);
        }
        this.isCurrentlyStreaming = true;
    }

    public void setUpCameraViews() {
        this.isCurrentlyStreaming = true;
        this.glSurfaceView = new MyGLSurfaceView((Context)this);
        FilterVault.updateUniformValues(defaultUniformValues);
        this.renderer = this.glSurfaceView.getRenderer();
        this.renderer.setFilters(filterIndices);
        this.renderer.updateFilterVariables(settingsOptions);
        this.glSurfaceView.setOnTouchListener((View.OnTouchListener)new OnSwipeTouchListener((Context)this, this.size.x){

            @Override
            public void launchUpdateBox() {
            }

            @Override
            public void onLeftScreenSwipe(int n) {
                FilterActivity.access$000()[0] = n + filterIndices[0];
                FilterActivity.this.updateFilters(1);
            }

            @Override
            public void onRightScreenSwipe(int n) {
                FilterActivity.access$000()[1] = n + filterIndices[1];
                FilterActivity.this.updateFilters(3);
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return this.gestureDetector.onTouchEvent(motionEvent);
            }
        });
        this.replaceCurrentView((View)this.glSurfaceView);
    }

    public void startCamera(int n) {
        this.surface = new SurfaceTexture(n);
        this.surface.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener)this);
        this.renderer.setSurface(this.surface);
        this.cameraAdapter.setupCamera(this.surface);
    }

    public void updateFilters(int n) {
        FilterVault.updateUniformValues(defaultUniformValues);
        this.fixFilterGivenIndex(0);
        this.fixFilterGivenIndex(1);
        this.renderer.updateFilters(filterIndices);
        for (Toast toast : this.bread) {
            if (toast == null) continue;
            toast.cancel();
        }
        this.bread.clear();
        if (n > 0) {
            if (n < 3) {
                this.bread.add((Object)this.createFilterToast(0, -this.size.x / 4));
            }
            if (n > 1) {
                this.bread.add((Object)this.createFilterToast(1, this.size.x / 4));
            }
        }
    }

}

