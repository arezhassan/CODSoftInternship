package com.example.sunshineschoolsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnGetStarted;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    Animation slide_down,slide_up;
    TextView textView, textView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void init(){
        btnGetStarted=findViewById(R.id.btnGetStarted);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        slide_down= AnimationUtils.loadAnimation(this,R.anim.slide_down);
        slide_up=AnimationUtils.loadAnimation(this,R.anim.slide_up);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView.setAnimation(slide_down);
        textView2.setAnimation(slide_up);

    }
    protected void onStart() {
        super.onStart();
      if(mAuth.getCurrentUser()!=null){
            Intent i=new Intent(MainActivity.this,Home.class);
            startActivity(i);
            finish();
        }
    }



}