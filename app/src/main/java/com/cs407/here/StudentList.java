package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    private ArrayList<String> displayNames;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Context context = getApplicationContext();
        //context.deleteDatabase("users");
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        /*dbHelper.createAccount("master", "instructor", "0", "", "", "");
        dbHelper.createAccount("Jane Doe", "student", "0", "CS407", "", "");
        dbHelper.createAccount("Varun", "student", "0", "CS407", "", "");
        dbHelper.createAccount("John Doe", "student", "0", "CS407", "", "");
        dbHelper.createAccount("SYDNEY BENCK", "student", "0", "CS407", "", "");*/



        displayNames = new ArrayList<>();
        ArrayList<StudentInfo> userList = dbHelper.readStudents("student");

        Intent intent = getIntent();
        String instructor_name = intent.getStringExtra("instructor name");

        StudentInfo instructor = dbHelper.getStudentInfo(instructor_name);
        displayNames = new ArrayList<>();
        for(StudentInfo studentInfo: userList) {
            if(studentInfo.class1.equals(instructor.class1)) {
                displayNames.add(String.format("Name: %s\t Points: %s\n", studentInfo.getName(), studentInfo.getPoints()));
            }
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNames);
        ListView studentListView = (ListView) findViewById(R.id.studentList);
        studentListView.setAdapter(adapter);
    }

    public void goToStudentUpdatePoints(View view) {
        Intent intent = new Intent(this, StudentUpdatePoints.class);
        startActivity(intent);
    }

    public void goToCreateStudent(View view) {
        Intent intent = new Intent(this, CreateStudent.class);
        startActivity(intent);
    }

}