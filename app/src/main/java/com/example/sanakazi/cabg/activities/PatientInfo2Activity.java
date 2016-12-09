package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PatientInfo2Activity extends AppCompatActivity {
    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.range1)
    EditText range1;
    @Bind(R.id.range2) EditText range2;
    @Bind(R.id.range3) EditText range3;
    int age;
    String name="";
    @Bind(R.id.txt_name)
    TextView txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_patient_info2);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent  = getIntent();
        age = intent.getIntExtra("age",0);
        name=intent.getStringExtra("name");

        events();

    }

    private void events(){

        txt_name.setText(String.valueOf(name));
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(range1.getText().toString().isEmpty() || range2.getText().toString().isEmpty() || range3.getText().toString().isEmpty())
                {
                    Toast.makeText(PatientInfo2Activity.this,"Please enter all values" ,Toast.LENGTH_SHORT).show();
                }

                else {
                    double grad = Double.parseDouble(range1.getText().toString());
                    double v_a = Double.parseDouble(range2.getText().toString());
                    double j_v = Double.parseDouble(range3.getText().toString());


                    if (grad < 0 || grad > 200) {
                        Toast.makeText(PatientInfo2Activity.this, "Please enter valid values for gradient", Toast.LENGTH_SHORT).show();
                    } else if (v_a < -2.5 || v_a > 2.6) {
                        Toast.makeText(PatientInfo2Activity.this, "Please enter valid values for valve area", Toast.LENGTH_SHORT).show();
                    } else if (j_v < 2.0 || j_v > 11.3) {
                        Toast.makeText(PatientInfo2Activity.this, "Please enter valid values for jet velocity", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(PatientInfo2Activity.this, PatientSeverityActivity.class);
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
