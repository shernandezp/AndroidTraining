package com.example.mycanada;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SensorFragment extends Fragment {

    Spinner sensorList;
    ArrayList arrayList;
    ArrayAdapter adapter;
    TextView textView;
    SensorManager sm = null;
    List list;
    String sensorName = "";
    Integer selectedIndex = 0;

    public SensorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        sensorList = view.findViewById(R.id.spinnerSensor);
        textView = view.findViewById(R.id.textViewSensor);

        arrayList = new ArrayList();
        arrayList.add("Accelerometer");
        arrayList.add("Proximity");
        arrayList.add("Temperature");
        arrayList.add("Light");
        arrayList.add("Humidity");
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        sensorList.setAdapter(adapter);
        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

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

                    sm.registerListener(sel, (Sensor) list.get(selectedIndex), SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Toast.makeText(getContext(), "Error: No " + sensorName, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
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