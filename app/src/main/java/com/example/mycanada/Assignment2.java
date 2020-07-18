package com.example.mycanada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Assignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
    }

    public void button2Click(View view) {
        EditText text = findViewById(R.id.editText1);
        EditText text2 = findViewById(R.id.editText2);
        EditText text3 = findViewById(R.id.editText3);

        Toast.makeText(Assignment2.this, text.getText().toString() + "\n" + text2.getText().toString() + "\n" + text3.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void button3Click(View view) {
        EditText text = findViewById(R.id.editText1);
        EditText text2 = findViewById(R.id.editText2);
        EditText text3 = findViewById(R.id.editText3);
        TextView result = findViewById(R.id.textView);
        result.setText("");

        result.append(text.getText().toString() + "\n");
        result.append(text2.getText().toString() + "\n");
        result.append(text3.getText().toString()+ "\n");
    }
}