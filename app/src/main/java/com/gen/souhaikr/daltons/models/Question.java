/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.gen.souhaikr.daltons.models;

public class Question {
    private String ANSWER;
    private String De;
    private int ID;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String Pr;

    public Question() {
    }

    public Question(String string2, String string3, String string4, String string5) {
        this.OPTA = string2;
        this.OPTB = string3;
        this.OPTC = string4;
        this.OPTD = string5;
    }

    public Question(String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        this.OPTA = string2;
        this.OPTB = string3;
        this.OPTC = string4;
        this.OPTD = string5;
        this.ANSWER = string6;
        this.Pr = string7;
        this.De = string8;
    }

    public String getANSWER() {
        return this.ANSWER;
    }

    public String getDe() {
        return this.De;
    }

    public int getID() {
        return this.ID;
    }

    public String getOPTA() {
        return this.OPTA;
    }

    public String getOPTB() {
        return this.OPTB;
    }

    public String getOPTC() {
        return this.OPTC;
    }

    public String getOPTD() {
        return this.OPTD;
    }

    public String getPr() {
        return this.Pr;
    }

    public void setANSWER(String string2) {
        this.ANSWER = string2;
    }

    public void setDe(String string2) {
        this.De = string2;
    }

    public void setID(int n) {
        this.ID = n;
    }

    public void setOPTA(String string2) {
        this.OPTA = string2;
    }

    public void setOPTB(String string2) {
        this.OPTB = string2;
    }

    public void setOPTC(String string2) {
        this.OPTC = string2;
    }

    public void setOPTD(String string2) {
        this.OPTD = string2;
    }

    public void setPr(String string2) {
        this.Pr = string2;
    }
}

