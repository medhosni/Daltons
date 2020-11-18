/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 */
package com.gen.souhaikr.daltons.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.gen.souhaikr.daltons.models.Question;
import java.util.ArrayList;
import java.util.List;

public class dbHelper
extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "triviaQuiz";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase dbase;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private void addQuestions() {
        Question question = new Question("9", "2", "3", "5", "5", "2", "");
        this.addQuestion(question);
        Question question2 = new Question("0", "5", "3", "8", "3", "5", "");
        this.addQuestion(question2);
        Question question3 = new Question("5", "17", "15", "9", "15", "17", "");
        this.addQuestion(question3);
        Question question4 = new Question("2", "21", "74", "4", "74", "21", "");
        this.addQuestion(question4);
        Question question5 = new Question("9", "2", "6", "26", "26", "6", "2");
        this.addQuestion(question5);
        Question question6 = new Question("9", "2", "4", "42", "42", "2", "4");
        this.addQuestion(question6);
        Question question7 = new Question("20", "3", "5", "35", "35", "5", "3");
        this.addQuestion(question7);
        Question question8 = new Question("96", "50", "6", "9", "96", "6", "9");
        this.addQuestion(question8);
        Question question9 = new Question("purple and red", "purple", "red", "grey", "purple and red", "purple", "red");
        this.addQuestion(question9);
        Question question10 = new Question("purple and red", "purple", "red", "grey", "purple and red", "purple", "red");
        this.addQuestion(question10);
    }

    public void addQuestion(Question question) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer", question.getANSWER());
        contentValues.put("prot", question.getPr());
        contentValues.put("deut", question.getDe());
        contentValues.put("opta", question.getOPTA());
        contentValues.put("optb", question.getOPTB());
        contentValues.put("optc", question.getOPTC());
        contentValues.put("optd", question.getOPTD());
        this.dbase.insert("quest", null, contentValues);
    }

    public List<Question> getAllQuestions() {
        ArrayList arrayList = new ArrayList();
        this.dbase = this.getReadableDatabase();
        Cursor cursor = this.dbase.rawQuery("SELECT  * FROM quest", null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setID(cursor.getInt(0));
                question.setANSWER(cursor.getString(1));
                question.setPr(cursor.getString(2));
                question.setDe(cursor.getString(3));
                question.setOPTA(cursor.getString(4));
                question.setOPTB(cursor.getString(5));
                question.setOPTC(cursor.getString(6));
                question.setOPTD(cursor.getString(7));
                arrayList.add((Object)question);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.dbase = sQLiteDatabase;
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quest ( id INTEGER PRIMARY KEY AUTOINCREMENT, answer TEXT, prot TEXT, deut TEXT, opta TEXT, optb TEXT, optc TEXT, optd TEXT)");
        this.addQuestions();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS quest");
        this.onCreate(sQLiteDatabase);
    }

    public int rowcount() {
        return this.getWritableDatabase().rawQuery("SELECT  * FROM quest", null).getCount();
    }
}

