package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText edt1,edt2;
    private Button login,signup;
    private TextView t1;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        edt1 = findViewById(R.id.emailET);
        edt2 = findViewById(R.id.passwordET);
        login = findViewById(R.id.loginBtn);
        signup = findViewById(R.id.signupBtn);
        t1 = findViewById(R.id.showMsg);
        progress = findViewById(R.id.progressBar);
        progress.setVisibility(View.GONE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt1.getText().toString();
                String password = edt2.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    edt1.setError("required");
                    edt2.setError("required");
                }
                progress.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,Welcome.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        t1.setText(e.getLocalizedMessage());
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt1.getText().toString();
                String password = edt2.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    edt1.setError("required");
                    edt2.setError("required");
                }
                progress.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progress.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Signup Sucssesful", Toast.LENGTH_SHORT).show();
                    }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        t1.setText(e.getLocalizedMessage());
                    }
                });
            }
        });
    }
}