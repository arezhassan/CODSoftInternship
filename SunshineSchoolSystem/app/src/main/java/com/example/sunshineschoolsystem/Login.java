package com.example.sunshineschoolsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button btnLogin;
    EditText etPass, etReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        progressBar.setVisibility(View.GONE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=etPass.getText().toString();
                String reg=etReg.getText().toString();
                if (pass.length()>=8 && reg.length()>=8){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(reg,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Intent i=new Intent(Login.this,Home.class);
                                startActivity(i);
                                finish();
                            }else{
                                progressBar.setVisibility(View.GONE);
                                etPass.setError("Invalid Password");
                                etReg.setError("Invalid Registration");
                            }
                        }
                    });

                }else if(pass.length()<8 || reg.length()<8){
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(Login.this, "Invalid Password or Registration", Toast.LENGTH_SHORT).show();
                    etPass.setError("Make sure your password is more than 8 digits");
                }
            }
        });

    }
    private void init(){
        btnLogin=findViewById(R.id.btnLogin);
        etPass=findViewById(R.id.etPass);
        etReg=findViewById(R.id.etReg);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);


    }
}