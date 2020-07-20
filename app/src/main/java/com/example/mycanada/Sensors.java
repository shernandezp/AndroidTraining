package com.example.mycanada;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Sensors extends AppCompatActivity {

    Spinner sensorList;
    ArrayList arrayList;
    ArrayAdapter adapter;
    TextView textView;
    SensorManager sm = null;
    List list;
    String sensorName = "";
    Integer selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);
        sensorList = findViewById(R.id.sensorList);
        textView = findViewById(R.id.textResult);

        arrayList = new ArrayList();
        arrayList.add("Accelerometer");
        arrayList.add("Proximity");
        arrayList.add("Temperature");
        arrayList.add("Light");
        arrayList.add("Humidity");
        adapter = new ArrayAdapter(Sensors.this, android.R.layout.simple_list_item_1, arrayList);
        sensorList.setAdapter(adapter);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
                        sensorName = "Accelerometer";
                        selectedIndex = 0;
                        break;
                    case 1:
                        list = sm.getSensorList(Sensor.TYPE_PROXIMITY);
                        sensorName = "Proximity Sensor";
                        selectedIndex = 1;
                        break;
                    case 2:
                        list = sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
                        sensorName = "Temperature Sensor";
                        selectedIndex = 2;
                        break;
                    case 3:
                        list = sm.getSensorList(Sensor.TYPE_LIGHT);
                        sensorName = "Light Sensor";
                        selectedIndex = 3;
                        break;
                    case 4:
                        list = sm.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
                        sensorName = "Humidity Sensor";
                        selectedIndex = 4;
                        break;
                }
                if (list.size() > 0) {
                    sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Toast.makeText(getBaseContext(), "Error: No " + sensorName, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            switch (selectedIndex) {
                case 0:
                    textView.setText("x: " + values[0] + "\ny: " + values[1] + "\nz: " + values[2]);
                    break;
                case 1:
                    textView.setText("x: " + values[0]);
                    break;
            }
        }
    };
}