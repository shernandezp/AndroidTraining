package com.example.mycanada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Service extends AppCompatActivity {

    Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        serviceIntent = new Intent(Service.this, MyService.class);
    }

    public void onClickStart(View view) {
        startService(serviceIntent);
    }

    public void onClickStop(View view) {
        stopService(serviceIntent);
    }
}