package com.example.bulbapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bulbapp.bulbapplib.ServiceManager;
import com.example.bulbapp.bulbapplib.models.BasicLight;
import com.example.bulbapp.bulbapplib.services.library.GenericLightService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorLightChangerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorLightChangerFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GenericLightService genericLightService;
    private SensorManager mSensorManager;
    private Sensor mTempSensor;
    private int sensorValue = 0;
    private boolean isActive = false;

    public SensorLightChangerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemperatureLightChangerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorLightChangerFragment newInstance(String param1, String param2) {
        SensorLightChangerFragment fragment = new SensorLightChangerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        genericLightService = ServiceManager.get().getGenericLightService();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isActive){
                    Log.i("TAG", "Works " + sensorValue);
                    genericLightService.updateAllLightsToNewLight(new BasicLight(sensorValue, 0, 0, 0));
                }
            }
        }, 0, 3000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor_light_changer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        isActive = true;
        mSensorManager.registerListener(this, mTempSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(this);
        isActive = false;
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != Sensor.TYPE_LIGHT) {
            return;
        }
        sensorValue = (int)sensorEvent.values[0];
        ImageView ivSensorBulb = getView().findViewById(R.id.ivSensorBulb);
        TextView tvSensorLightSensorValue = getView().findViewById(R.id.tvSensorLightSensorValue);
        if (sensorValue > 255){
            sensorValue = 255;
        }
        ivSensorBulb.setColorFilter(Color.rgb(sensorValue, sensorValue, sensorValue));
        tvSensorLightSensorValue.setText("" + sensorValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}