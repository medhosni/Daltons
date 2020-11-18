/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.graphics.Color
 *  java.lang.Math
 *  java.lang.Object
 */
package com.gen.souhaikr.daltons;

import android.graphics.Color;

public class ColorTool {
    public boolean closeMatch(int n, int n2, int n3) {
        if (Math.abs((int)(Color.red((int)n) - Color.red((int)n2))) > n3) {
            return false;
        }
        if (Math.abs((int)(Color.green((int)n) - Color.green((int)n2))) > n3) {
            return false;
        }
        return Math.abs((int)(Color.blue((int)n) - Color.blue((int)n2))) <= n3;
    }
}

