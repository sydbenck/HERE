package com.cs407.here;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper {
    static SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public static void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users " +
                "(name TEXT, status TEXT, points TEXT, class1 TEXT, class2 TEXT, class3 TEXT)");
    }

    public void createAccount(String name, String status, String points, String class1, String class2, String class3) {
        createTable();
        sqLiteDatabase.execSQL("INSERT INTO users (name, status, points, class1, class2, class3) VALUES (?, ?, ?, ?, ?, ?)",
                new String[]{name, status, points, class1, class2, class3});
    }

    public void updateClass(String name, String status, String points, String class1, String class2, String class3) {
        createTable();
        sqLiteDatabase.execSQL("UPDATE users set class1=?, class2=?, class3=? where name=? and status=? and points=?",
                new String[]{class1, class2, class3, name, status, points});
    }

    public void updatePoints(String name, String status, String points, String class1, String class2, String class3) {
        createTable();
        sqLiteDatabase.execSQL("UPDATE users set points=? where status=? and name=? and class1=? and class2=? and class3=?",
                new String[]{points, status, name, class1, class2, class3});
    }

    public ArrayList<String> readStudents(String status) {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE status LIKE ?", new String[]{"%" + status + "%"});
        int nameIndex = c.getColumnIndex("name");
        c.moveToFirst();
        ArrayList<String> userList = new ArrayList<>();
        while(!c.isAfterLast()) {
            userList.add(c.getString(nameIndex));
            c.moveToNext();
        }
        c.close();
        return userList;
    }

}
