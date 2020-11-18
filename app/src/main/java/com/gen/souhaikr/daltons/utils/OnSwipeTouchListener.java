/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 */
package com.gen.souhaikr.daltons.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class OnSwipeTouchListener
implements View.OnTouchListener {
    public final GestureDetector gestureDetector;
    private final int midWidth;

    public OnSwipeTouchListener(Context context, int n) {
        this.gestureDetector = new GestureDetector(context, (GestureDetector.OnGestureListener)new GestureListener());
        this.midWidth = n / 2;
    }

    public abstract void launchUpdateBox();

    public abstract void onLeftScreenSwipe(int var1);

    public abstract void onRightScreenSwipe(int var1);

    private final class GestureListener
    extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private GestureListener() {
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            block5 : {
                try {
                    float f3 = motionEvent2.getY() - motionEvent.getY();
                    if (!(Math.abs((float)f3) <= 100.0f) && !(Math.abs((float)f2) <= 100.0f)) {
                        if (motionEvent.getRawX() < (float)OnSwipeTouchListener.this.midWidth) {
                            OnSwipeTouchListener.this.onLeftScreenSwipe(Integer.signum((int)((int)f3)));
                        } else {
                            OnSwipeTouchListener.this.onRightScreenSwipe(Integer.signum((int)((int)f3)));
                        }
                        break block5;
                    }
                    OnSwipeTouchListener.this.launchUpdateBox();
                    return false;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    }

}

