package com.example.sanakazi.cabg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.activities.MainActivity;
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
    @Bind(R.id.range1) EditText range1;
    @Bind(R.id.range2) EditText range2;
    @Bind(R.id.range3) EditText range3;
    int age;
    String name="";
    @Bind(R.id.txt_name)
    TextView txt_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_info2, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Patient Info");

        Bundle bundle  = getArguments();
        age = bundle.getInt("age");
        name=bundle.getString("name");
        events();

        return view;
    }

    private void events(){

        txt_name.setText(String.valueOf(name));
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(range1.getText().toString().isEmpty() || range2.getText().toString().isEmpty() || range3.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Please enter all values" ,Toast.LENGTH_SHORT).show();
                }

        else {
                    double grad = Double.parseDouble(range1.getText().toString());
                    double v_a = Double.parseDouble(range2.getText().toString());
                    double j_v = Double.parseDouble(range3.getText().toString());


                    if (grad < 0 || grad > 200) {
                        Toast.makeText(getActivity(), "Please enter valid values for gradient", Toast.LENGTH_SHORT).show();
                    } else if (v_a < -2.5 || v_a > 2.6) {
                        Toast.makeText(getActivity(), "Please enter valid values for valve area", Toast.LENGTH_SHORT).show();
                    } else if (j_v < 2.0 || j_v > 11.3) {
                        Toast.makeText(getActivity(), "Please enter valid values for jet velocity", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(getActivity(), PatientSeverityActivity.class);
                        intent.putExtra("age", age);
                        intent.putExtra("gradient", Double.parseDouble(range1.getText().toString()));
                        intent.putExtra("valve_area", Double.parseDouble(range2.getText().toString()));
                        intent.putExtra("jet_velocity", Double.parseDouble(range3.getText().toString()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
