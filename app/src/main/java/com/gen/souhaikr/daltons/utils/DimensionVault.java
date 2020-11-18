/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.gen.souhaikr.daltons.utils;

public class DimensionVault {
    public static final float[] BLUE = new float[]{0.0f, 0.0f, 1.0f};
    public static final float[] GREEN = new float[]{0.0f, 1.0f, 0.0f};
    public static final float[] RED = new float[]{1.0f, 0.0f, 0.0f};
    private static final float d2 = 0.0f;
    public static final short[] drawOrder;
    private static final float f0b = 1.0f;
    private static final float f1d = 0.0f;
    private static final float f2l = 0.25f;
    private static final float f3r = 0.75f;
    private static final float f4t = 0.0f;
    public static float[] fullTextureVertices;
    private static final float lb = 1.0f;
    private static final float ll = 0.25f;
    private static final float lr = 0.75f;
    private static final float lt = 0.0f;
    private static final float rb = 1.0f;
    private static final float rl = 0.25f;
    private static final float rr = 0.75f;
    private static final float rt;
    public static float[] textureVertices;
    public static final float[] textureVerticesLeft;
    public static final float[] textureVerticesRight;
    public static final float[] triangleVerticesLeft;
    public static final float[] triangleVerticesRight;
    public static float[] wholeTriangleVertices;

    static {
        fullTextureVertices = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
        textureVertices = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
        textureVerticesLeft = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
        textureVerticesRight = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
        triangleVerticesLeft = new float[]{-1.0f, 1.0f, 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 1.0f, 0.0f, -1.0f, -1.0f, -1.0f};
        triangleVerticesRight = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, -1.0f};
        wholeTriangleVertices = new float[]{-1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f};
        short[] arrs = new short[6];
        arrs[1] = 1;
        arrs[2] = 2;
        arrs[3] = 3;
        arrs[4] = 4;
        arrs[5] = 5;
        drawOrder = arrs;
    }
}

