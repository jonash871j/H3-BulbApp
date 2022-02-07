package com.example.bulbapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.bulbapp.bulbapplib.ServiceManager;
import com.example.bulbapp.bulbapplib.models.BasicLight;
import com.example.bulbapp.bulbapplib.services.library.GenericLightService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicLightChangerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicLightChangerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SeekBar seekBar;
    private GenericLightService genericLightService;

    public BasicLightChangerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicLightChangerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicLightChangerFragment newInstance(String param1, String param2) {
        BasicLightChangerFragment fragment = new BasicLightChangerFragment();
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
        return inflater.inflate(R.layout.fragment_basic_light_changer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        genericLightService = ServiceManager.get().getGenericLightService();

        seekBar = getView().findViewById(R.id.sbBrightness);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                genericLightService.updateAllLightsToNewLight(new BasicLight(seekBar.getProgress(), 0, 0, 0));
            }
        });
    }
}