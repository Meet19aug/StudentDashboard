package com.example.studentdashboard;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class  DBNoticeHelper extends SQLiteOpenHelper {
    public static final String DBName = "MyDB";
    public static final String TABLE_NAME = "generalNotice";
    public  DBNoticeHelper( Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table " + TABLE_NAME +"(id Integer primary key autoincrement, email Text,semester Text,msgTitle Text,message Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop Table if exists " + TABLE_NAME);
        onCreate(myDb);
    }

    public void addToGeneralNotice(String email, String semester, String msgTitle, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("semester", semester);
        values.put("msgTitle", msgTitle);
        values.put("message", message);
        try {
            db.insert(TABLE_NAME, null, values);
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }

    public void deleteToGeneralNotice(int id){
        System.out.println("Hello");
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID=" + id;
        db.execSQL(sql);
    }

    public ArrayList<StudentNoticeDomain> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";",null);
        ArrayList<StudentNoticeDomain> notes = new ArrayList<StudentNoticeDomain>();
        res.moveToFirst();
        while(res.isAfterLast() == false){
            @SuppressLint("Range") int id = res.getInt(res.getColumnIndex("id"));
            @SuppressLint("Range") String emailInfo = res.getString(res.getColumnIndex("email"));
            @SuppressLint("Range") String semesterInfo = res.getString(res.getColumnIndex("semester"));
            @SuppressLint("Range") String msgTitleInfo = res.getString(res.getColumnIndex("msgTitle"));
            @SuppressLint("Range") String messageInfo = res.getString(res.getColumnIndex("message"));
            StudentNoticeDomain note = new StudentNoticeDomain();
            note.setId(id);
            note.setEmail(emailInfo);
            note.setSemester(semesterInfo);
            note.setMsgTitle(msgTitleInfo);
            note.setMessage(messageInfo);
            notes.add(note);
            res.moveToNext();
        }
        return notes;
    }

    public void updateToGeneralNotice(int id, String newEmail, String newSemester, String newMsgTitile, String newMsg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        values.put("semester", newSemester);
        values.put("msgTitle", newMsgTitile);
        values.put("message", newMsg);
        try {
            db.update(TABLE_NAME, values, "ID=?", new String[]{id + ""});
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
