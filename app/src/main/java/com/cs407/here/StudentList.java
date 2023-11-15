package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    private ArrayList<String> displayNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        dbHelper.createAccount("master", "instructor", "0", "", "", "");
        dbHelper.createAccount("Jane Doe", "student", "0", "CS407", "", "");
        dbHelper.createAccount("Varun", "student", "0", "CS407", "", "");
        dbHelper.createAccount("John Doe", "student", "0", "CS407", "", "");
        ArrayList<String> userList = dbHelper.readStudents("master");

        displayNames = new ArrayList<>();
        for(String name: userList) {
            displayNames.add(String.format("Name:%s\n", name));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNames);
        ListView studentListView = (ListView) findViewById(R.id.studentList);
        studentListView.setAdapter(adapter);
    }
}