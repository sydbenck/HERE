package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
    }

    public void goToClassPage(View view){
        Intent intent = new Intent(this, ClassPage1.class);
        startActivity(intent);
    }
}