package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("com.cs407.here", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        StudentInfo studentInfo = dbHelper.getStudentInfo(name);
        String points = Integer.toString((Integer.parseInt(studentInfo.getPoints()) + 2));
        dbHelper.updatePoints(studentInfo.getName(), studentInfo.getStatus(), points, studentInfo.getClass1(),
                studentInfo.getClass2(), studentInfo.getClass3());
    }

    public void goToClassList(View view){
        Intent intent = new Intent(this, ClassList.class);
        startActivity(intent);
    }
}