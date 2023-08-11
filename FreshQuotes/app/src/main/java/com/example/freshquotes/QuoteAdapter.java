package com.example.freshquotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private Context context;
    private List<Quote> quoteList;

    public QuoteAdapter(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_quote, parent, false);
        return new QuoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quoteList.get(position);

        holder.tvQuote.setText(quote.getQuoteText());
        holder.imgSave.setOnClickListener(v -> {
            String selectedQuote= quote.getQuoteText();
            Intent intent = new Intent(context, DisplayQuoteActivity.class);
            intent.putExtra("selectedQuote", selectedQuote);
            context.startActivity(intent);

            // Handle the save button click for the specific quote
            // You can implement the saving functionality here
        });
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuote;
        ImageView imgSave;

        QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuote = itemView.findViewById(R.id.tvQuote);
            imgSave = itemView.findViewById(R.id.imgSave);
        }
    }
}
