package com.example.mycanada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hey!!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void onClickButton(View view) {
        EditText username = findViewById(R.id.editText1);
        EditText password = findViewById(R.id.editText2);
        if (username.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
            Toast.makeText(this, "Please insert username and password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, MainBlackBoard.class);
            startActivity(intent);
        }


    }

    public void onClickButton2(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
}