package com.list.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText signupEmail, signupPassword;
    Button signupButton;
    TextView goBackToLoginScreen;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();

        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupbutton);
        goBackToLoginScreen = findViewById(R.id.loginBack);


        goBackToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letsTakeUserBack();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }

    private void letsTakeUserBack() {
        Intent intent = new Intent(this, Login.class);
     startActivity(intent);
    }


    private void registerNewUser() {

        String emailString = signupEmail.getText().toString().trim();
        String passwordString = signupPassword.getText().toString().trim();

        if (TextUtils.isEmpty(emailString)) {
            signupEmail.setError("Signup field empty");
            signupEmail.requestFocus();

        }
        if (TextUtils.isEmpty(passwordString)) {

            signupPassword.setError("Password Empty");
            signupPassword.requestFocus();

        }
        try {
            firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(Signup.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Signup.this, "Some problem occured", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } catch (Exception exception) {

            Toast.makeText(this, "Username or Password Field Empty. Please Enter Them", Toast.LENGTH_SHORT).show();
        }


//        private void letsTakeUserBack () {
//
//            Intent intent = new Intent(this, Login.class);
//            startActivity(intent);
//        }


    }

}