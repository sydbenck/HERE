package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Absent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);
    }
    public void goToClassList(View view){
        Intent intent = new Intent(this, ClassList.class);
        startActivity(intent);
    }
}