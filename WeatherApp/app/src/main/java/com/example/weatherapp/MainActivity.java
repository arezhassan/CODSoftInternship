package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;

    private TextView tvName,tvCity,tvDescription,tvTemperature, tvWind,tvHumidity;
    private TextView tvName1,tvCity1,tvDescription1,tvTemperature1;

    private ProgressBar progressBar;
    double longi,lati;
    LottieAnimationView animationView,animation_view3, animation_view4,cardload;
    LottieAnimationView animationView1;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText etCity;
    Button btnSearchCity;
    ImageView  imgRefresh;
    ImageView  imgRefresh1;
    CardView cardView,card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        animationView.playAnimation();
        requestLocation();
        requestWeather(lati,longi);



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                requestLocation();
            }
        });
        imgRefresh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String city= etCity.getText().toString();
                if(city.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    etCity.setText("");
                    etCity.requestFocus();
                    Toast.makeText(MainActivity.this, "City cannot be empty", Toast.LENGTH_SHORT).show();
                    etCity.setError("Enter City");
                }else{
                requestWeatherByCity(city);
                etCity.setText("");
                etCity.clearFocus();
                progressBar.setVisibility(View.GONE);
            }}
        });





                btnSearchCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                       String city= etCity.getText().toString();
                        if(city.isEmpty()){
                            progressBar.setVisibility(View.GONE);
                            etCity.setText("");
                            etCity.requestFocus();
                            Toast.makeText(MainActivity.this, "City cannot be empty", Toast.LENGTH_SHORT).show();
                            etCity.setError("Enter City");
                        }else{
                       requestWeatherByCity(city);
                        etCity.setText("");
                        etCity.clearFocus();
                        progressBar.setVisibility(View.GONE);
                    }}
                });



    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvName = findViewById(R.id.tvName);
        tvCity = findViewById(R.id.tvCity);
        tvDescription = findViewById(R.id.tvDescription);
        etCity = findViewById(R.id.etCity);
        btnSearchCity = findViewById(R.id.btnSearchCity);
        imgRefresh = findViewById(R.id.imgRefresh);
        animationView = findViewById(R.id.animation_view);
        animationView1 = findViewById(R.id.animation_view1);
        imgRefresh1 = findViewById(R.id.imgRefresh1);
        tvTemperature1 = findViewById(R.id.tvTemperature1);
        tvName1 = findViewById(R.id.tvName1);
        tvCity1 = findViewById(R.id.tvCity1);
        tvDescription1 = findViewById(R.id.tvDescription1);
        cardView = findViewById(R.id.cardView);
        cardView.setVisibility(View.GONE);
        card1 = findViewById(R.id.card1);
        card1.setVisibility(View.GONE);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvWind = findViewById(R.id.tvWind);
        animation_view3 = findViewById(R.id.animation_view3);
        animation_view4 = findViewById(R.id.animation_view4);
        cardload = findViewById(R.id.cardload);
        int humidAnimationResourceId = R.raw.humid;
        int cardloadAnimationResourceId = R.raw.cardanimation;
        int windAnimationResourceId = R.raw.wind;
        animation_view3.setAnimation(humidAnimationResourceId);
        cardload.setAnimation(cardloadAnimationResourceId);
        animation_view4.setAnimation(windAnimationResourceId);
        cardload.playAnimation();
        animation_view3.playAnimation();
        animation_view4.playAnimation();







    }

    private void requestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            progressBar.setVisibility(View.VISIBLE);
            com.google.android.gms.location.LocationRequest locationRequest = new com.google.android.gms.location.LocationRequest();
            locationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000); // Update interval in milliseconds



            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    Location location = locationResult.getLastLocation();
                    // Use the location object (latitude and longitude)

                  longi=location.getLatitude();
                lati=location.getLongitude();

                }
            };

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void requestWeatherByCity(String cityName) {
        String apiKey = "baba6745a0280625b82fd1c10b4c4c9e";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();
                    connection.disconnect();

                    JSONObject responseJson = new JSONObject(response.toString());
                    // Extract weather information
                    double temperature = responseJson.getJSONObject("main").getDouble("temp");
                    double newtemp=temperature-273.15;
                    int finaltemp=(int) newtemp;
                    String weatherDescription = responseJson.getJSONArray("weather").getJSONObject(0).getString("description");
                    String city= responseJson.getString("name");
                    String weatherTitle = responseJson.getJSONArray("weather").getJSONObject(0).getString("main");


                    // Extract weather information (similar to previous implementation)
                    // ...

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int cloudyAnimationResourceId = R.raw.cloudy;
                            int sunnyAnimationResourceId = R.raw.sunny;
                            int rainAnimationResourceId = R.raw.rain;

                            if(weatherTitle.equals("Clear") || (weatherTitle.equals("Sunny"))){
                                animationView1.setAnimation(sunnyAnimationResourceId);
                                animationView1.playAnimation();

                            }
                            else if(weatherTitle.equals("Clouds")){
                                animationView1.setAnimation(cloudyAnimationResourceId);
                                animationView1.playAnimation();


                            }else if(weatherTitle.equals("Rain")){
                                animationView1.setAnimation(rainAnimationResourceId);
                                animationView1.playAnimation();

                            }else if(weatherTitle.equals("Snow")){

                            }

                            tvTemperature1.setText(" "+finaltemp+"°C");
                            tvName1.setText(" "+weatherTitle);
                            tvCity1.setText(" "+city);
                            tvDescription1.setText(" "+weatherDescription);


                            // Update UI with weather information (similar to previous implementation)
                            // ...
                            progressBar.setVisibility(View.GONE);
                            cardView.setVisibility(View.VISIBLE);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void requestWeather(double latitude, double longitude) {
        progressBar.setVisibility(View.VISIBLE);

        String apiKey = "baba6745a0280625b82fd1c10b4c4c9e";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();
                    connection.disconnect();

                    JSONObject responseJson = new JSONObject(response.toString());

                    // Extract weather information
                    double temperature = responseJson.getJSONObject("main").getDouble("temp");
                    double newtemp=temperature-273.15;
                    int finaltemp=(int) newtemp;
                    String weatherDescription = responseJson.getJSONArray("weather").getJSONObject(0).getString("description");
                    String city= responseJson.getString("name");
                    String weatherTitle = responseJson.getJSONArray("weather").getJSONObject(0).getString("main");
                    String humidity= responseJson.getJSONObject("main").getString("humidity");
                    String wind= responseJson.getJSONObject("wind").getString("speed");


                    // String weatherDescription = responseJson.getJSONArray("weather").getJSONObject(0).getString("description");

                    // Update UI on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int cloudyAnimationResourceId = R.raw.cloudy;
                            int sunnyAnimationResourceId = R.raw.sunny;
                            int rainAnimationResourceId = R.raw.rain;

                            if(weatherTitle.equals("Clear") || (weatherTitle.equals("Sunny"))){
                                animationView.setAnimation(sunnyAnimationResourceId);
                                animationView.playAnimation();

                            }
                            else if(weatherTitle.equals("Clouds")){
                                animationView.setAnimation(cloudyAnimationResourceId);
                                animationView.playAnimation();


                            }else if(weatherTitle.equals("Rain")){
                                animationView.setAnimation(rainAnimationResourceId);
                                animationView.playAnimation();

                            }else if(weatherTitle.equals("Snow")){

                            }

                            tvTemperature.setText(" "+finaltemp+"°C");
                            tvName.setText(" "+weatherTitle);
                            tvCity.setText(" "+city);
                            tvDescription.setText(" "+weatherDescription);
                            tvHumidity.setText(" "+humidity+"%");
                            tvWind.setText(" "+wind+"km/h");
                            cardload.setVisibility(View.GONE);
                            card1.setVisibility(View.VISIBLE);



                            // Update UI with weather information (similar to previous implementation)
                            // ...
                            progressBar.setVisibility(View.GONE);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }






}


