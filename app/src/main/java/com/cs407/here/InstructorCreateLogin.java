package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent = new Intent(this, StudentList.class);
        startActivity(intent);
    }
}