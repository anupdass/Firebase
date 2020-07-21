package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertData extends AppCompatActivity {

    private EditText edt1,edt2;
    private Button insbtn;

    private FirebaseUser  curentUser;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference userIdRef;
    private DatabaseReference employeeRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        edt1 = findViewById(R.id.nameEt);
        edt2 = findViewById(R.id.saleryEt);
        insbtn = findViewById(R.id.insetbtn);

        //database initilization
        curentUser = FirebaseAuth.getInstance().getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Users");
        if (curentUser != null){
            userIdRef = userRef.child(curentUser.getUid());
        }
        employeeRef = userIdRef.child("Employees");


        insbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt1.getText().toString();
                String salary = edt2.getText().toString();
                String emId = employeeRef.push().getKey();

                Employee employee = new Employee(emId,name,Double.parseDouble(salary));
                employeeRef.child(emId).setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(InsertData.this,Welcome.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InsertData.this, "Fail to insert", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}