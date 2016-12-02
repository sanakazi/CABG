package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.database.Cabg;
import com.example.sanakazi.cabg.database.DaysAddedBean;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DaysAddedActivity extends AppCompatActivity {

    @Bind(R.id.txt_days_added)
    TextView txt_days_added;
    @Bind(R.id.btn_next)
    Button btn_next;

    Cabg myDb;
    DaysAddedBean obj_myDb;
    double gradient_value,valve_area,jet_velocity;
    double new_grad , new_val_area,new_jet_vel;
    int age,chosen_age;
    int daysAddedValue;
    private static String TAG= DaysAddedActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_added);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent  = getIntent();
        age = intent.getIntExtra("age",0);
        chosen_age=intent.getIntExtra("chosen_age",0);

        gradient_value = intent.getDoubleExtra("gradient",0);
        valve_area = intent.getDoubleExtra("valve_area",0);
        jet_velocity = intent.getDoubleExtra("jet_velocity",0);

        new_grad = intent.getDoubleExtra("new_gradient",0);
        new_val_area = intent.getDoubleExtra("new_valve_area",0);
        new_jet_vel = intent.getDoubleExtra("new_jet_velocity",0);


        Log.w(TAG, age + ", " + gradient_value);
        calculateDays();
        events();
    }

    private  void events()
    {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DaysAddedActivity.this,Summary2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("age",age);
                intent.putExtra("chosen_age",chosen_age);

                intent.putExtra("gradient" ,gradient_value);
                intent.putExtra("valve_area" ,valve_area);
                intent.putExtra("jet_velocity" ,jet_velocity);

                intent.putExtra("new_gradient" ,new_grad);
                intent.putExtra("new_valve_area" ,new_jet_vel);
                intent.putExtra("new_jet_velocity" ,new_val_area);
                startActivity(intent);
            }
        });
    }

    private void calculateDays()
    {
      if(age>70)
      {
          daysAddedValue=105;
          Log.w(TAG,"CASE 1");
      }
        else if(age<50)
      {
          daysAddedValue=105;
          Log.w(TAG,"CASE 2");
      }
      else if(gradient_value>50)
      {
          daysAddedValue=105;
          Log.w(TAG,"CASE 3");
      }
        else if(age>=50 && age <=70 && gradient_value<=50)
      {
          Log.w(TAG,"CASE 4");
          databaseEvents();
      }


    }

    private void databaseEvents()
    {
        myDb = new Cabg(DaysAddedActivity.this);
        myDb.createDatabase();
        myDb.open();

        obj_myDb = myDb.getDaysAdded(String.valueOf(gradient_value));
        Log.w(TAG,"days added are "+ obj_myDb.getDaysAdded()) ;
        daysAddedValue=Integer.parseInt(obj_myDb.getDaysAdded());
        txt_days_added.setText(String.valueOf(daysAddedValue));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home : onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
