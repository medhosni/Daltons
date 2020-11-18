/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collection
 */
package com.gen.souhaikr.daltons.utils;

import com.gen.souhaikr.daltons.utils.UniformPair;
import java.util.ArrayList;
import java.util.Collection;

public class Filter {
    private final String filterShader;
    private final String id;
    private int myProgram;
    private final ArrayList<UniformPair> myUnifromPairs = new ArrayList();

    public Filter(String string2, String string3) {
        this.id = string2;
        this.filterShader = string3;
    }

    public void addUniformPairs(ArrayList<UniformPair> arrayList) {
        this.myUnifromPairs.addAll(arrayList);
    }

    public String getFilterShader() {
        return this.filterShader;
    }

    public int getProgram() {
        return this.myProgram;
    }

    public ArrayList<UniformPair> getUniformPairs() {
        return this.myUnifromPairs;
    }

    public void setProgram(int n) {
        this.myProgram = n;
    }

    public String toString() {
        return this.id;
    }
}

