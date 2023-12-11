package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentUpdatePoints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update_points);
    }

    public void goToStudentListPoints(View view) {
        EditText newPoints = (EditText) findViewById(R.id.newPoints);
        EditText studentNamePoints = (EditText) findViewById(R.id.studentNamePoints);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        StudentInfo studentInfo = dbHelper.getStudentInfo(studentNamePoints.getText().toString().trim());
        String putpoints = newPoints.getText().toString().trim();
        dbHelper.updatePoints(studentInfo.getName(), studentInfo.getStatus(), putpoints, studentInfo.getClass1(), studentInfo.getClass2(), studentInfo.getClass3());
        Intent intent = new Intent(this, StudentList.class);
        startActivity(intent);
    }
}