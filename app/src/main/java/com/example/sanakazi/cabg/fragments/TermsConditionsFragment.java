package com.example.sanakazi.cabg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.activities.AppIntroActivity;
import com.example.sanakazi.cabg.others.AboutUs;

/**
 * Created by SanaKazi on 12/7/2016.
 */
public class TermsConditionsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms_conditions, container, false);
        getActivity().setTitle("Patient Info");
        TextView txt_disclaimer = (TextView)view.findViewById(R.id.txt_disclaimer);
        txt_disclaimer.setText(String.valueOf(AboutUs.disclaimer));

        Button btn_accept = (Button)view.findViewById(R.id.btn_accept);


        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientInfo1Fragment patientInfo1Fragment = new PatientInfo1Fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, patientInfo1Fragment).commit();

            }
        });


        return view;
    }

}
