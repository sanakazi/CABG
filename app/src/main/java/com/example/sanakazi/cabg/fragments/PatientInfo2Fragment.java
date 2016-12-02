package com.example.sanakazi.cabg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.activities.PatientSeverityActivity;
import com.example.sanakazi.cabg.activities.Summary1Activity;
import com.example.sanakazi.cabg.others.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sanakazi on 11/30/2016.
 */
public class PatientInfo2Fragment extends Fragment {
    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.et_gradient) EditText et_gradient;
    @Bind(R.id.et_valve_area) EditText et_valve_area;
    @Bind(R.id.et_jet_velocity) EditText et_jet_velocity;
    int age;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_info2, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle  = getArguments();
        age = bundle.getInt("age");
        events();

        return view;
    }

    private void events(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_gradient.getText().toString().isEmpty() || et_valve_area.getText().toString().isEmpty() || et_jet_velocity.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Please enter valid data " , Toast.LENGTH_SHORT).show();
                }
                else {

                    double grad = Double.parseDouble(et_gradient.getText().toString());
                    double v_a = Double.parseDouble(et_valve_area.getText().toString());
                    double j_v = Double.parseDouble(et_jet_velocity.getText().toString());

                    Intent intent = new Intent(getActivity(), PatientSeverityActivity.class);
                    intent.putExtra("age", age);
                    intent.putExtra("gradient", Double.parseDouble(et_gradient.getText().toString()));
                    intent.putExtra("valve_area", Double.parseDouble(et_valve_area.getText().toString()));
                    intent.putExtra("jet_velocity", Double.parseDouble(et_jet_velocity.getText().toString()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }
            }
        });
    }
}
