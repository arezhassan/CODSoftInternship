package com.example.weatherapp;


import android.Manifest;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WeatherWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_REFRESH = "com.example.weatherapp.ACTION_REFRESH";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            // Set up a refresh button click intent
            Intent refreshIntent = new Intent(context, WeatherWidgetProvider.class);
            refreshIntent.setAction(ACTION_REFRESH);
            refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{widgetId});
            PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.imgRefresh1, refreshPendingIntent);

            // Update UI components in the RemoteViews
            updateWidgetUI(remoteViews, context, widgetId);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (ACTION_REFRESH.equals(intent.getAction())) {
            int[] widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            for (int widgetId : widgetIds) {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                updateWidgetUI(remoteViews, context, widgetId);
                appWidgetManager.partiallyUpdateAppWidget(widgetId, remoteViews);
            }
        }
    }

    private void updateWidgetUI(RemoteViews remoteViews, Context context, int widgetId) {
        // Fetch weather information and update UI components
        // Implement this part based on your original code

        // ...

        // Example: Updating TextViews
        remoteViews.setTextViewText(R.id.tvCity1, "City Name");
        remoteViews.setImageViewResource(R.id.imageView2,R.drawable.sunny);
        remoteViews.setTextViewText(R.id.tvTemperature1, "25°C");
        remoteViews.setTextViewText(R.id.tvName1, "Clear");
        remoteViews.setTextViewText(R.id.tvDescription1, "Clear sky");
    }
}
