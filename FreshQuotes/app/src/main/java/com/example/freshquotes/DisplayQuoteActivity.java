package com.example.freshquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DisplayQuoteActivity extends AppCompatActivity {
    TextView tvDisplayQuote;
    CardView card;
    ImageView imgShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quote);
         tvDisplayQuote= findViewById(R.id.tvDisplayQuote);
         card= findViewById(R.id.card);
         imgShare= findViewById(R.id.imgShare);




        if (getIntent() != null && getIntent().hasExtra("selectedQuote")) {
            String selectedQuote = getIntent().getStringExtra("selectedQuote");
            tvDisplayQuote.setText(selectedQuote);
        }
        imgShare.setOnClickListener(v -> {
            Bitmap cardScreenshot = takeScreenshotOfView(card);
            // Proceed to share the screenshot
            shareScreenshot(cardScreenshot);
        });

    }
    private Bitmap takeScreenshotOfView(View view) {
        Bitmap viewScreenshot = Bitmap.createBitmap(
                view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888
        );

        Canvas canvas = new Canvas(viewScreenshot);
        view.draw(canvas);

        return viewScreenshot;
    }
    private void shareScreenshot(Bitmap screenshot) {
        String fileName = "screenshot.png";
        File imagePath = new File(getCacheDir(), fileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(imagePath);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri screenshotUri = FileProvider.getUriForFile(
                this,
                "com.example.freshquotes.provider",
                imagePath
        );

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(shareIntent, "Share Screenshot"));
    }


}