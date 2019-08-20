package com.list.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sharedpreferences.PreferenceManager;

public class Login extends AppCompatActivity {

    Button login, signup;
    EditText loginEmail, loginPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginbutton);
        signup = findViewById(R.id.signupbutton);
        loginEmail = findViewById(R.id.loginemail);
        loginPassword = findViewById(R.id.loginpass);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                proceedSignupActivity();
            }
        });


    }

    private void proceedSignupActivity() {

        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private void loginUser() {

        final String logEmail = loginEmail.getText().toString().trim();
        final String logPass = loginPassword.getText().toString().trim();


            firebaseAuth.signInWithEmailAndPassword(logEmail, logPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        String username = loginEmail.getText().toString();
                        String password = loginPassword.getText().toString();
                        PreferenceManager.getInstance(Login.this).saveUser(username, password);

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {

                        Toast.makeText(Login.this, "Invalid Username or Password. Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser !=null){
            startActivity(new Intent(Login.this,MainActivity.class));
        }

    }
    //    @Override
//
//protected void onStart(){
//
//      super.onStart();
//
//      if (PreferenceManager.getInstance(Login.this).isUserLoggedIn()){
//
//          Intent intent = new Intent(Login.this,add.class);
//      }
//
//    }
    }
