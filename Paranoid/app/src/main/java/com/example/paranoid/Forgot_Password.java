package com.example.paranoid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    Button button;
    EditText editText;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        button = findViewById(R.id.buttonSendCode);
        editText = findViewById(R.id.editTextEmail);
        progressBar = findViewById(R.id.forgetPasswordProgressBar);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(email)){
                    ResetPassword();
                } else {
                    editText.setError("Email field can't be empty");
                }
            }
        });
    }
    private void ResetPassword(){
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Forgot_Password.this, "Reset Password link has been sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Forgot_Password.this, Login.class);
                startActivity(intent);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Forgot_Password.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}