package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sanakazi.cabg.R;

public class Summary2Activity extends AppCompatActivity {

    double gradient_value,valve_area,jet_velocity;
    double new_grad , new_val_area,new_jet_vel;
    int age,chosen_age;
    private static String TAG= Summary2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary2);
        Intent intent  = getIntent();
        age = intent.getIntExtra("age",0);
        chosen_age=intent.getIntExtra("chosen_age",0);

        gradient_value = intent.getDoubleExtra("gradient",0);
        valve_area = intent.getDoubleExtra("valve_area",0);
        jet_velocity = intent.getDoubleExtra("jet_velocity",0);

        new_grad = intent.getDoubleExtra("new_gradient",0);
        new_val_area = intent.getDoubleExtra("new_valve_area",0);
        new_jet_vel = intent.getDoubleExtra("new_jet_velocity",0);

        Log.w(TAG,age +","+ chosen_age+","+  gradient_value+"," + valve_area +","+ jet_velocity );
        Log.w(TAG,new_grad +","+ new_val_area+","+  new_jet_vel);
    }
}
