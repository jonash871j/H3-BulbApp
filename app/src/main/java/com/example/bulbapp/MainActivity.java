package com.example.bulbapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.Volley;
import com.example.bulbapp.bulbapplib.ServiceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceManager.Initialize(Volley.newRequestQueue(this));
        // BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // NavController navController = Navigation.findNavController(this, R.id.fragment);
        // NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}