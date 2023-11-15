package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToClassList(View view){
        Intent intent = new Intent(this, ClassList.class);
        startActivity(intent);
    }

    public void goToStudentList(View view) {
        Intent intent = new Intent(this, StudentList.class);
        startActivity(intent);
    }

}