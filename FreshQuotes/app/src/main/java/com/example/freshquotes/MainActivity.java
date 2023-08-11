package com.example.freshquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView quotesrv;
    ArrayList<Quote> quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);
        init();
        quotes=new ArrayList<>();
        quotes.add(new Quote("What is the difference between a chicken and a fish?" +
                "Chicken and fish are different in that they are both a type of animal." +
                "The chicken is a type of animal and the fish is a type of animal."));
        quotes.add(new Quote("You only live once, but if you do it right, once is enough."));
        quotes.add(new Quote("Your time is limited, so don’t waste it living someone else’s life. Don’t be trapped by dogma -" +
                "which is living with the results of other people’s thinking. - Steve Jobs "));
        quotes.add(new Quote("You never really learn much from hearing yourself speak"));
        quotes.add(new Quote("Life imposes things on you that you can’t control, but you still have the choice of how you’re going to live through this. — Celine Dion"));
        QuoteAdapter adapter = new QuoteAdapter(MainActivity.this,quotes);
        quotesrv.setLayoutManager(new LinearLayoutManager(this));
        quotesrv.setAdapter(adapter);






    }
    private void init(){
        quotesrv = findViewById(R.id.quotesrv);

    }
}