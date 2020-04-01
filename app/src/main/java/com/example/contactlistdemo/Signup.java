package com.example.contactlistdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private EditText mName, mEmail,mPassword;
    private TextView backToLogin;
    private Button signup;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        mName = findViewById(R.id.rname);
        mEmail = findViewById(R.id.remail);
        mPassword = findViewById(R.id.rpassword);
        backToLogin =findViewById(R.id.createText2);
        signup =(Button) findViewById(R.id.signupbtn);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Signup.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Signup.this,"SignUp Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, MainActivity.class));
                        }
                    }
                });
            }
        });

    }
}
