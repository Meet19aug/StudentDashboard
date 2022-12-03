package com.example.studentdashboard;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";
    public static final String USER_TABLE = "usersInfo";
    public DBHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table "+ USER_TABLE+ " (username Text,useremail Text primary key,userdepartment Text, usersemester Text, userdivision Text,userid Text,userphone Text, userpassword Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop Table if exists " + USER_TABLE);
        onCreate(myDb);
    }

    public Boolean insertData (String name, String email, String dept,String sem, String divi, String id,String phone, String password)   {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",name);
        contentValues.put("useremail",email);
        contentValues.put("userdepartment",dept);
        contentValues.put("usersemester",sem);
        contentValues.put("userdivision",divi);
        contentValues.put("userid",id);
        contentValues.put("userphone",phone);
        contentValues.put("userpassword",password);

        long result = myDb.insert(USER_TABLE,null,contentValues);
        if(result==-11)
                return false;
        else
            return true;
    }

    public StudentInfoDomain findStudentDetails(String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("Select * from "+ USER_TABLE+ " where useremail = ?", new String[]{email});
        if (res.getCount() > 0) {
            res.moveToFirst();
            StudentInfoDomain obj = new StudentInfoDomain();
            @SuppressLint("Range") String usernameInfo = res.getString(res.getColumnIndex("username"));
            @SuppressLint("Range") String useremailInfo = res.getString(res.getColumnIndex("useremail"));
            @SuppressLint("Range") String userdepartmentInfo = res.getString(res.getColumnIndex("userdepartment"));
            @SuppressLint("Range") String useridInfo = res.getString(res.getColumnIndex("userid"));
            @SuppressLint("Range") String userdivisionInfo = res.getString(res.getColumnIndex("userdivision"));
            @SuppressLint("Range") String usersemesterInfo = res.getString(res.getColumnIndex("usersemester"));
            obj.setUsername(usernameInfo);
            obj.setUseremail(useremailInfo);
            obj.setUserdepartment(userdepartmentInfo);
            obj.setUserid(useridInfo);
            obj.setUserdivision(userdivisionInfo);
            obj.setUsersemester(usersemesterInfo);
            System.out.println("DbHelper: after fetching details : "+ obj.username);
            return obj;
        }
        else
            System.out.println("User Not Found!!!!");
            return null;

    }





    public Boolean checkemail( String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("Select * from "+ USER_TABLE+ " where useremail = ?", new String[]{email});
        if (res.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemailpassword( String email, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        System.out.println("Inside checking functionality..");
        Cursor res = myDb.rawQuery("Select * from "+ USER_TABLE+" where useremail = ? and userpassword = ?", new String[]{email,password});
        if (res.getCount() > 0)
            return true;
        else
            return false;
    }
}
