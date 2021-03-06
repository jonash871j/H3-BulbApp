package com.example.bulbapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bulbapp.bulbapplib.ServiceManager;
import com.example.bulbapp.bulbapplib.models.BasicLight;
import com.example.bulbapp.bulbapplib.services.library.GenericLightService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RGBLightChangerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RGBLightChangerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GenericLightService genericLightService;

    public RGBLightChangerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RGBLightChangerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RGBLightChangerFragment newInstance(String param1, String param2) {
        RGBLightChangerFragment fragment = new RGBLightChangerFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_r_g_b_light_changer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        genericLightService = ServiceManager.get().getGenericLightService();

        ImageView ivRgbBulb = getView().findViewById(R.id.ivRgbBulb);
        SeekBar sbRed = getView().findViewById(R.id.sbRed);
        SeekBar sbGreen = getView().findViewById(R.id.sbGreen);
        SeekBar sbBlue = getView().findViewById(R.id.sbBlue);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ivRgbBulb.setColorFilter(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                genericLightService.updateAllLightsToNewLight(new BasicLight(0, sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }
        };
        sbRed.setOnSeekBarChangeListener(seekBarChangeListener);
        sbGreen.setOnSeekBarChangeListener(seekBarChangeListener);
        sbBlue.setOnSeekBarChangeListener(seekBarChangeListener);
    }
}