package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InstructorCreateLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_create_login);
    }

    public void goToInstructorCreate(View view) {
        Intent intent = new Intent(this, InstructorCreatePage.class);
        startActivity(intent);
    }
    public void goToInstructorPage(View view) {
        EditText instructor_name = (EditText) findViewById(R.id.instructorLoginName);
        /*Intent intent = new Intent(this, StudentList.class);
        intent.putExtra("instructor name", ""+instructor_name.getText());
        startActivity(intent);*/
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        ArrayList<StudentInfo> instructors = dbHelper.readStudents("instructor");
        boolean found = false;
        for (StudentInfo studentInfo: instructors) {
            if((studentInfo.getName().trim()).equals(instructor_name.getText().toString().trim())) {
                found = true;
                break;
            }
        }
        if(found == true) {
            Intent intent = new Intent(this, StudentList.class);
            intent.putExtra("instructor name", ""+instructor_name.getText());
            startActivity(intent);
        }
        else {
            Toast.makeText(InstructorCreateLogin.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
        }

    }
}