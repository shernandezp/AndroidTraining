package com.example.mycanada;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    Button b3;
    CheckBox checkBox;
    EditText text;
    EditText text2;
    EditText text3;
    EditText username;
    EditText password;
    RadioGroup rdGender;
    Spinner list;
    ArrayList arrayList;
    ArrayAdapter adapter;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        b3 = findViewById(R.id.button7);
        checkBox = findViewById(R.id.checkBox2);
        text = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        username = findViewById(R.id.editTextUser);
        password = findViewById(R.id.editTextPwd);
        rdGender = findViewById(R.id.radioGroup);
        list = findViewById(R.id.list);
        arrayList = new ArrayList();
        arrayList.add("Bachelor");
        arrayList.add("Master");
        arrayList.add("Doctor");
        adapter = new ArrayAdapter(SignUp.this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(adapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = arrayList.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void onClickButton7(View view) {

        sharedPreferences = getSharedPreferences("mycanadaapp", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (validatePassword()) {
            if (checkBox.isChecked()) {
                Intent intent = new Intent(SignUp.this, Profile.class);
                editor.putString("username", username.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.putString("phone", text.getText().toString());
                editor.putString("blood", text2.getText().toString());
                editor.putString("birth", text3.getText().toString());
                editor.putString("gender", GetGender());
                editor.putString("qualification", selectedItem);
                editor.commit();

                /*Store on Database*/
                User user = new User(SignUp.this, username.getText().toString(), password.getText().toString(), text.getText().toString(), text3.getText().toString(), 1, 1);
                user.InsertUser();

                startActivity(intent);
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
                alert.setTitle("Warning!");
                alert.setMessage("Please indicate that you have read and agree to the Terms and Conditions and Privacy Policy");
                alert.setIcon(android.R.drawable.stat_sys_warning);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        }
    }

    public void onClickCheck(View view) {

    }

    public void onTextViewClick(View view) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog date = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = format.format(calendar.getTime());
                text3.setText(strDate);
            }
        }, mYear, mMonth, mDay);
        date.show();
    }

    //Private methods

    private String GetGender() {
        String gender = "";
        switch (rdGender.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                gender = "Male";
                break;
            case R.id.radioButton2:
                gender = "Female";
                break;
        }
        return gender;
    }

    public void onClickTextView2(View view) {
        Intent intent = new Intent(SignUp.this, TermsConditions.class);
        startActivity(intent);
    }

    public boolean validatePassword() {
        boolean result = true;
        int passwordLength = password.getText().toString().length();
        int usernameLength = username.getText().toString().length();
        if (passwordLength < 5) {
            Toast.makeText(SignUp.this, "Password is too short.", Toast.LENGTH_SHORT).show();
            result = false;
        }
        if (usernameLength < 4) {
            Toast.makeText(SignUp.this, "Username is too short.", Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }
}