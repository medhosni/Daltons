/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.graphics.SurfaceTexture
 *  android.hardware.Camera
 *  android.hardware.Camera$Parameters
 *  java.io.IOException
 *  java.lang.Object
 */
package com.gen.souhaikr.daltons.utils;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import java.io.IOException;

public class CameraAdapter {
    private Camera mCamera = null;

    public void destroyCamera() {
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public void setupCamera(SurfaceTexture surfaceTexture) {
        this.destroyCamera();
        this.mCamera = Camera.open();
        Camera.Parameters parameters = this.mCamera.getParameters();
        this.mCamera.setParameters(parameters);
        try {
            this.mCamera.setPreviewTexture(surfaceTexture);
            this.mCamera.startPreview();
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }
}

