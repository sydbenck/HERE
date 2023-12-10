package com.cs407.here;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InstructorCreatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_create_page);
    }

    public void goToInstructorLoginPage(View view) {
        EditText instructor_name = (EditText) findViewById(R.id.instructorName);
        EditText instructor_class = (EditText) findViewById(R.id.instructorClass);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        dbHelper.createAccount(""+(instructor_name.getText()), "instructor", "0", ""+(instructor_class.getText()), "", "");
        Intent intent = new Intent(this, InstructorCreateLogin.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, InstructorCreateLogin.class);
        startActivity(intent);
    }

}