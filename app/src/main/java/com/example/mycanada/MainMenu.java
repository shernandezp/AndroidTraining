package com.example.mycanada;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuresource, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onItem1Click(MenuItem item) {
        Intent intent = new Intent(MainMenu.this, Sensors.class);
        startActivity(intent);
    }

    public void onItem2Click(MenuItem item) {
        Intent intent = new Intent(MainMenu.this, SignUp.class);
        startActivity(intent);
    }
}