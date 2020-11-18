/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.support.v7.app.AppCompatActivity
 *  android.util.Log
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.Window
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  org.opencv.android.CameraBridgeViewBase
 *  org.opencv.android.CameraBridgeViewBase$CvCameraViewFrame
 *  org.opencv.android.JavaCameraView
 *  org.opencv.android.OpenCVLoader
 *  org.opencv.core.Core
 *  org.opencv.core.CvType
 *  org.opencv.core.Mat
 *  org.opencv.core.Size
 *  org.opencv.imgproc.Imgproc
 */
package com.gen.souhaikr.daltons;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class CameraActivity
extends AppCompatActivity
implements CameraBridgeViewBase.CvCameraViewListener2 {
    private static final String TAG = "OCVSample::Activity";
    Mat LMS;
    Mat actRGBVec;
    Mat lmsMat;
    Mat lmsResVec;
    private boolean mIsJavaCamera = true;
    private MenuItem mItemSwitchCamera = null;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback((Context)this){

        @Override
        public void onManagerConnected(int n) {
            if (n != 0) {
                super.onManagerConnected(n);
                return;
            }
            Log.i((String)CameraActivity.TAG, (String)"OpenCV loaded successfully");
            CameraActivity.this.mOpenCvCameraView.enableView();
        }
    };
    private CameraBridgeViewBase mOpenCvCameraView;
    Mat mRgba;
    Mat mRgbaF;
    Mat mRgbaT;

    public CameraActivity() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Instantiated new ");
        stringBuilder.append((Object)this.getClass());
        Log.i((String)TAG, (String)stringBuilder.toString());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame cvCameraViewFrame) {
        this.mRgba = cvCameraViewFrame.rgba();
        Core.transpose((Mat)this.mRgba, (Mat)this.mRgbaT);
        Imgproc.resize((Mat)this.mRgbaT, (Mat)this.mRgbaF, (Size)this.mRgbaF.size(), (double)0.0, (double)0.0, (int)0);
        Core.flip((Mat)this.mRgbaF, (Mat)this.mRgba, (int)1);
        int n = 0;
        do {
            block5 : {
                block4 : {
                    try {
                        if (n >= this.mRgba.rows()) break block4;
                        break block5;
                    }
                    catch (Exception exception) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Error rgb to lms conversion! ");
                        stringBuilder.append(exception.getMessage());
                        Log.d((String)"ImageHandler", (String)stringBuilder.toString());
                    }
                }
                return this.mRgba;
            }
            for (int n2 = 0; n2 < this.mRgba.cols(); ++n2) {
                this.actRGBVec.put(0, 0, this.mRgba.get(n, n2));
                Core.gemm((Mat)this.lmsMat, (Mat)this.actRGBVec, (double)1.0, null, (double)0.0, (Mat)this.lmsResVec, (int)0);
                this.LMS.put(n, n2, this.lmsResVec.get(0, 0));
                Imgproc.cvtColor((Mat)this.lmsResVec, (Mat)this.mRgba, (int)0);
            }
            ++n;
        } while (true);
    }

    @Override
    public void onCameraViewStarted(int n, int n2) {
        this.mRgba = new Mat(n2, n, CvType.CV_8UC4);
        this.mRgbaF = new Mat(n2, n, CvType.CV_8UC4);
        this.mRgbaT = new Mat(n, n, CvType.CV_8UC4);
        this.LMS = new Mat(3, 3, CvType.CV_32FC1);
        this.actRGBVec = new Mat(1, 3, CvType.CV_32FC1);
        this.lmsResVec = new Mat(1, 3, CvType.CV_32FC1);
        this.lmsMat = new Mat(n2, n, CvType.CV_64FC3);
    }

    @Override
    public void onCameraViewStopped() {
        this.mRgba.release();
    }

    protected void onCreate(Bundle bundle) {
        Log.i((String)TAG, (String)"called onCreate");
        super.onCreate(bundle);
        this.getWindow().addFlags(128);
        this.setContentView(R.layout.activity_camera);
        this.mOpenCvCameraView = (JavaCameraView)this.findViewById(R.id.camera);
        this.mOpenCvCameraView.setVisibility(View.VISIBLE);
        this.mOpenCvCameraView.setCvCameraViewListener((CameraBridgeViewBase.CvCameraViewListener2)this);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mOpenCvCameraView != null) {
            this.mOpenCvCameraView.disableView();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mOpenCvCameraView != null) {
            this.mOpenCvCameraView.disableView();
        }
    }

    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d((String)TAG, (String)"Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync((String)"3.0.0", (Context)this, (LoaderCallbackInterface)this.mLoaderCallback);
            return;
        }
        Log.d((String)TAG, (String)"OpenCV library found inside package. Using it!");
        this.mLoaderCallback.onManagerConnected(0);
    }

}

