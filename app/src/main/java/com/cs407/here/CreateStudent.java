package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
    }

    public void goToStudentListCreate(View view) {
        EditText className = (EditText) findViewById(R.id.editTextClass);
        EditText studentNameCreate = (EditText) findViewById(R.id.editTextStudent);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        dbHelper.createAccount(""+(studentNameCreate.getText().toString().trim()), "student", "0", ""+(className.getText().toString().trim()), "", "");
        Intent intent = new Intent(this, StudentList.class);
        startActivity(intent);
    }
}