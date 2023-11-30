package com.cs407.here;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ClassList extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name;
    Button signOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        name = findViewById(R.id.welcomeText);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        SharedPreferences sharedPreferences = getSharedPreferences("com.cs407.here", Context.MODE_PRIVATE);
        if(acct != null){
            String personName = acct.getDisplayName();
            //String personEmail = acct.getEmail(); maybe use??
            sharedPreferences.edit().putString("name", personName).apply();
            name.setText("Welcome, " + personName);
        }
    }

    public void goToClassPage(View view){
        Intent intent = new Intent(this, ClassPage1.class);
        startActivity(intent);
    }

    public void logout(View view){
        gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}