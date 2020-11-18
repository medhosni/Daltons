/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.graphics.SurfaceTexture
 *  android.opengl.GLES20
 *  android.opengl.GLSurfaceView
 *  android.opengl.GLSurfaceView$Renderer
 *  android.opengl.Matrix
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  javax.microedition.khronos.egl.EGLConfig
 *  javax.microedition.khronos.opengles.GL10
 */
package com.gen.souhaikr.daltons.utils;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.gen.souhaikr.daltons.FilterActivity;
import com.gen.souhaikr.daltons.utils.CameraStreaming;
import com.gen.souhaikr.daltons.utils.DimensionVault;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer
implements GLSurfaceView.Renderer {
    CameraStreaming cameraStreamLeft;
    CameraStreaming cameraStreamRight;
    final FilterActivity delegate;
    private Integer leftFilterIndex = 0;
    private Integer rightFilterIndex = 0;
    private SurfaceTexture surface;
    int texture;
    private int[] updatedVars = new int[5];

    public MyRenderer(FilterActivity filterActivity) {
        this.delegate = filterActivity;
    }

    public static int createTexture() {
        int[] arrn = new int[1];
        GLES20.glGenTextures((int)1, (int[])arrn, (int)0);
        GLES20.glBindTexture((int)36197, (int)arrn[0]);
        GLES20.glTexParameterf((int)36197, (int)10241, (float)9729.0f);
        GLES20.glTexParameterf((int)36197, (int)10240, (float)9729.0f);
        GLES20.glTexParameteri((int)36197, (int)10242, (int)33071);
        GLES20.glTexParameteri((int)36197, (int)10243, (int)33071);
        return arrn[0];
    }

    public static int loadShader(int n, String string2) {
        int n2 = GLES20.glCreateShader((int)n);
        GLES20.glShaderSource((int)n2, (String)string2);
        GLES20.glCompileShader((int)n2);
        return n2;
    }

    public void onDrawFrame(GL10 gL10) {
        float[] arrf = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        GLES20.glClear((int)16640);
        this.surface.updateTexImage();
        Matrix.rotateM((float[])arrf, (int)0, (float)this.updatedVars[1], (float)1.0f, (float)0.0f, (float)0.0f);
        Matrix.rotateM((float[])arrf, (int)0, (float)this.updatedVars[2], (float)0.0f, (float)1.0f, (float)0.0f);
        Matrix.rotateM((float[])arrf, (int)0, (float)this.updatedVars[3], (float)0.0f, (float)0.0f, (float)1.0f);
        this.cameraStreamLeft.draw(arrf);
        this.cameraStreamRight.draw(arrf);
    }

    @SuppressLint(value={"ClickableViewAccessibility"})
    public void onSurfaceChanged(GL10 gL10, int n, int n2) {
        GLES20.glViewport((int)0, (int)0, (int)n, (int)n2);
    }

    public void onSurfaceCreated(GL10 gL10, EGLConfig eGLConfig) {
        this.texture = MyRenderer.createTexture();
        this.cameraStreamLeft = new CameraStreaming(this.texture, DimensionVault.triangleVerticesLeft, DimensionVault.textureVerticesLeft);
        this.cameraStreamRight = new CameraStreaming(this.texture, DimensionVault.triangleVerticesRight, DimensionVault.textureVerticesRight);
        GLES20.glClearColor((float)0.5f, (float)0.5f, (float)0.5f, (float)1.0f);
        this.cameraStreamLeft.updateFilterSelection(this.leftFilterIndex);
        this.cameraStreamRight.updateFilterSelection(this.rightFilterIndex);
        this.delegate.startCamera(this.texture);
    }

    public void setCameraSurface(SurfaceTexture surfaceTexture) {
        this.surface = surfaceTexture;
    }

    public void setFilters(int[] arrn) {
        this.leftFilterIndex = arrn[0];
        this.rightFilterIndex = arrn[1];
    }

    public void setSurface(SurfaceTexture surfaceTexture) {
        this.surface = surfaceTexture;
    }

    public void updateFilterVariables(int[] arrn) {
        this.updatedVars = arrn;
    }

    public void updateFilters(int[] arrn) {
        this.leftFilterIndex = arrn[0];
        this.rightFilterIndex = arrn[1];
        this.cameraStreamLeft.updateFilterSelection(arrn[0]);
        this.cameraStreamRight.updateFilterSelection(arrn[1]);
    }
}

