/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.opengl.GLSurfaceView
 *  android.opengl.GLSurfaceView$Renderer
 *  java.io.PrintStream
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 */
package com.gen.souhaikr.daltons.utils;

import android.content.Context;
import android.opengl.GLSurfaceView;
import com.gen.souhaikr.daltons.FilterActivity;
import com.gen.souhaikr.daltons.utils.MyRenderer;
import java.io.PrintStream;

public class MyGLSurfaceView
extends GLSurfaceView {
    final MyRenderer renderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);
        this.renderer = new MyRenderer((FilterActivity)context);
        this.setRenderer((GLSurfaceView.Renderer)this.renderer);
        this.setRenderMode(0);
    }

    public MyRenderer getRenderer() {
        return this.renderer;
    }

    public boolean performClick() {
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hardware ");
        stringBuilder.append(this.isHardwareAccelerated());
        printStream.println(stringBuilder.toString());
        return super.performClick();
    }
}

