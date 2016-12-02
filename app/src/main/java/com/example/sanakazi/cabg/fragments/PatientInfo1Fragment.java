package com.example.sanakazi.cabg.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.others.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sanakazi on 11/29/2016.
 */
public class PatientInfo1Fragment extends Fragment{

    @Bind(R.id.btn_next)
    Button btn_next;
    PatientInfo2Fragment patientInfo2Fragment;
    int age=60;

  
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_info1, container, false);
        ButterKnife.bind(this, view);

        events();

        return view;
    }


        private void events(){
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    patientInfo2Fragment = new PatientInfo2Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("age",age);;
                    patientInfo2Fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,patientInfo2Fragment ).addToBackStack("abc").commit();
                }
            });
        }
}
