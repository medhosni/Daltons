/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.Buffer
 *  java.nio.ByteBuffer
 *  java.nio.ByteOrder
 *  java.nio.FloatBuffer
 *  java.nio.ShortBuffer
 *  java.util.ArrayList
 *  java.util.Random
 */
package com.gen.souhaikr.daltons.utils;

import android.opengl.GLES20;
import com.gen.souhaikr.daltons.utils.Filter;
import com.gen.souhaikr.daltons.utils.FilterVault;
import com.gen.souhaikr.daltons.utils.MyRenderer;
import com.gen.souhaikr.daltons.utils.UniformPair;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Random;

public class CameraStreaming {
    static final int COORDS_PER_VERTEX = 2;
    private Filter activeFilter;
    private final ArrayList<Filter> allFilters;
    private int counter;
    private final int counterMod;
    private final ShortBuffer drawListBuffer;
    private final short[] drawOrder;
    private final Random generator;
    private final float[] ijkRand;
    private int mColorHandle;
    private int mPositionHandle;
    private final int mProgram1;
    private final int mProgram2;
    private int mTextureCoordHandle;
    private final int texture;
    private final FloatBuffer textureVerticesBuffer;
    private ArrayList<UniformPair> uniformPairs;
    private final FloatBuffer vertexBuffer;
    private final String vertexShaderCode = "attribute vec4 position;uniform mat4 uMVPMatrix;   \t\nattribute vec2 inputTextureCoordinate;varying vec2 textureCoordinate;void main(){gl_Position = uMVPMatrix*position;textureCoordinate = inputTextureCoordinate;}";
    private final int vertexStride = 8;

    public CameraStreaming(int n, float[] arrf, float[] arrf2) {
        short[] arrs = new short[6];
        arrs[1] = 1;
        arrs[2] = 2;
        arrs[3] = 3;
        arrs[4] = 4;
        arrs[5] = 5;
        this.drawOrder = arrs;
        this.generator = new Random();
        this.ijkRand = new float[]{0.0f, 0.0f, 0.0f};
        this.counterMod = 10;
        this.counter = 0;
        this.texture = n;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)(4 * arrf.length));
        byteBuffer.order(ByteOrder.nativeOrder());
        this.vertexBuffer = byteBuffer.asFloatBuffer();
        this.vertexBuffer.put(arrf);
        this.vertexBuffer.position(0);
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect((int)(2 * this.drawOrder.length));
        byteBuffer2.order(ByteOrder.nativeOrder());
        this.drawListBuffer = byteBuffer2.asShortBuffer();
        this.drawListBuffer.put(this.drawOrder);
        this.drawListBuffer.position(0);
        ByteBuffer byteBuffer3 = ByteBuffer.allocateDirect((int)(4 * arrf2.length));
        byteBuffer3.order(ByteOrder.nativeOrder());
        this.textureVerticesBuffer = byteBuffer3.asFloatBuffer();
        this.textureVerticesBuffer.put(arrf2);
        this.textureVerticesBuffer.position(0);
        int n2 = MyRenderer.loadShader(35633, "attribute vec4 position;uniform mat4 uMVPMatrix;   \t\nattribute vec2 inputTextureCoordinate;varying vec2 textureCoordinate;void main(){gl_Position = uMVPMatrix*position;textureCoordinate = inputTextureCoordinate;}");
        this.allFilters = FilterVault.getAllFilters();
        for (Filter filter : this.allFilters) {
            int n3 = MyRenderer.loadShader(35632, filter.getFilterShader());
            int n4 = GLES20.glCreateProgram();
            GLES20.glAttachShader((int)n4, (int)n2);
            GLES20.glAttachShader((int)n4, (int)n3);
            GLES20.glLinkProgram((int)n4);
            filter.setProgram(n4);
            GLES20.glEnable((int)2929);
        }
        int n5 = MyRenderer.loadShader(35632, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(vec3(1.0)-tex.rgb, tex.a);}");
        this.mProgram1 = GLES20.glCreateProgram();
        GLES20.glAttachShader((int)this.mProgram1, (int)n2);
        GLES20.glAttachShader((int)this.mProgram1, (int)n5);
        GLES20.glLinkProgram((int)this.mProgram1);
        GLES20.glEnable((int)2929);
        int n6 = MyRenderer.loadShader(35632, FilterVault.fs_GrayCCIR);
        this.mProgram2 = GLES20.glCreateProgram();
        GLES20.glAttachShader((int)this.mProgram2, (int)n2);
        GLES20.glAttachShader((int)this.mProgram2, (int)n6);
        GLES20.glLinkProgram((int)this.mProgram2);
        GLES20.glEnable((int)2929);
        this.uniformPairs = FilterVault.getAllUniformPairs();
    }

    public void draw(float[] arrf) {
        int n = this.activeFilter.getProgram();
        GLES20.glUseProgram((int)n);
        GLES20.glActiveTexture((int)33984);
        GLES20.glBindTexture((int)36197, (int)this.texture);
        for (UniformPair uniformPair : this.uniformPairs) {
            GLES20.glUniform1f((int)GLES20.glGetUniformLocation((int)n, (String)uniformPair.getKey()), (float)uniformPair.getValue());
        }
        GLES20.glUniform1f((int)GLES20.glGetUniformLocation((int)n, (String)"time"), (float)this.generator.nextFloat());
        this.counter = 1 + this.counter;
        if (this.counter % 10 == 0) {
            this.ijkRand[0] = this.generator.nextFloat();
            this.ijkRand[1] = this.generator.nextFloat();
            this.ijkRand[2] = this.generator.nextFloat();
        }
        GLES20.glUniform1f((int)GLES20.glGetUniformLocation((int)n, (String)"irand"), (float)this.ijkRand[0]);
        GLES20.glUniform1f((int)GLES20.glGetUniformLocation((int)n, (String)"jrand"), (float)this.ijkRand[1]);
        GLES20.glUniform1f((int)GLES20.glGetUniformLocation((int)n, (String)"krand"), (float)this.ijkRand[2]);
        GLES20.glUniformMatrix4fv((int)GLES20.glGetUniformLocation((int)n, (String)"uMVPMatrix"), (int)1, (boolean)false, (float[])arrf, (int)0);
        this.mPositionHandle = GLES20.glGetAttribLocation((int)n, (String)"position");
        GLES20.glEnableVertexAttribArray((int)this.mPositionHandle);
        GLES20.glVertexAttribPointer((int)this.mPositionHandle, (int)2, (int)5126, (boolean)false, (int)8, (Buffer)this.vertexBuffer);
        this.mTextureCoordHandle = GLES20.glGetAttribLocation((int)n, (String)"inputTextureCoordinate");
        GLES20.glEnableVertexAttribArray((int)this.mTextureCoordHandle);
        GLES20.glVertexAttribPointer((int)this.mTextureCoordHandle, (int)2, (int)5126, (boolean)false, (int)8, (Buffer)this.textureVerticesBuffer);
        this.mColorHandle = GLES20.glGetAttribLocation((int)n, (String)"s_texture");
        GLES20.glDrawElements((int)4, (int)this.drawOrder.length, (int)5123, (Buffer)this.drawListBuffer);
        GLES20.glDisableVertexAttribArray((int)this.mPositionHandle);
        GLES20.glDisableVertexAttribArray((int)this.mTextureCoordHandle);
    }

    public void updateFilterSelection(int n) {
        this.activeFilter = (Filter)this.allFilters.get(n);
        this.updateVariables();
    }

    public void updateVariables() {
        this.uniformPairs = FilterVault.getAllUniformPairs();
    }
}

