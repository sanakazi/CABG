package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.database.Cabg;
import com.example.sanakazi.cabg.database.CabjBean;
import com.example.sanakazi.cabg.database.DataBaseHelper;
import com.example.sanakazi.cabg.database.DaysAddedBean;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Summary1Activity extends AppCompatActivity {
    @Bind(R.id.op_cabg) TextView op_cabg;@Bind(R.id.op_cabgavr_cur) TextView op_cabgavr_cur;@Bind(R.id.op_cabgavr_fut) TextView op_cabgavr_fut;
    @Bind(R.id.ps_cabg) TextView ps_cabg; @Bind(R.id.ps_cabgavr_cur) TextView ps_cabgavr_cur;@Bind(R.id.ps_cabgavr_fut) TextView ps_cabgavr_fut;
    @Bind(R.id.rfc_cabg) TextView rfc_cabg; @Bind(R.id.rfc_cabgavr_cur) TextView rfc_cabgavr_cur;@Bind(R.id.rfc_cabgavr_fut) TextView rfc_cabgavr_fut;
    @Bind(R.id.btn_next) Button btn_next;
    @Bind(R.id.txt_newage) TextView txt_newage; @Bind(R.id.txt_age) TextView txt_age;



    //region variables used
    Cabg myDb;
    CabjBean obj_myDb;
    private static String TAG= Summary1Activity.class.getSimpleName();
    int current_age,chosen_age;
    double gradient,valve_area,jet_velocity;
    double new_grad , new_val_area,new_jet_vel;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary1);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        current_age=intent.getIntExtra("age",0);
        chosen_age=intent.getIntExtra("chosen_age",0);

        gradient = intent.getDoubleExtra("gradient",0);
        valve_area = intent.getDoubleExtra("valve_area",0);
        jet_velocity = intent.getDoubleExtra("jet_velocity",0);

        new_grad = intent.getDoubleExtra("new_gradient",0);
        new_val_area = intent.getDoubleExtra("new_valve_area",0);
        new_jet_vel = intent.getDoubleExtra("new_jet_velocity",0);


        Log.w(TAG,"Gradient is" +gradient);

        ButterKnife.bind(this);

        txt_age.setText(String.valueOf(current_age));
        txt_newage.setText(String.valueOf(chosen_age));
        databaseEvents();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Summary1Activity.this,DaysAddedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("age",current_age);
                intent.putExtra("chosen_age",chosen_age);

                intent.putExtra("gradient" ,gradient);
                intent.putExtra("valve_area" ,valve_area);
                intent.putExtra("jet_velocity" ,jet_velocity);

                intent.putExtra("new_gradient" ,new_grad);
                intent.putExtra("new_valve_area" ,new_jet_vel);
                intent.putExtra("new_jet_velocity" ,new_val_area);

                startActivity(intent);
            }
        });



    }



    private void databaseEvents()
    {
        myDb = new Cabg(Summary1Activity.this);
        myDb.createDatabase();
        myDb.open();

        setCurrentValues();
        setFutureValues();


    }

    private  void setCurrentValues()
    {
        obj_myDb = myDb.getCurrentValues(String.valueOf(current_age));
        Log.w(TAG,obj_myDb.getCurrent_OM_CABG() + " , " + obj_myDb.getCurrent_OM_CABGAVR()) ;

        op_cabg.setText(String.valueOf(obj_myDb.getCurrent_OM_CABG()));
        op_cabgavr_cur.setText(String.valueOf(obj_myDb.getCurrent_OM_CABGAVR()));

        ps_cabg.setText(String.valueOf(obj_myDb.getCurrent_PS_CABG()));
        ps_cabgavr_cur.setText(String.valueOf(obj_myDb.getCurrent_PS_CABGAVR()));

        rfc_cabg.setText(String.valueOf(obj_myDb.getCurrent_RF_CABG()));
        rfc_cabgavr_cur.setText(String.valueOf(obj_myDb.getCurrent_RF_CABGAVR()));

    }

    private void  setFutureValues()
    {
        obj_myDb = myDb.getFutureValues(String.valueOf(chosen_age));
        Log.w(TAG,obj_myDb.getFuture_OM_CABGAVR() ) ;

        op_cabgavr_fut.setText(String.valueOf(obj_myDb.getFuture_OM_CABGAVR()));
        ps_cabgavr_fut.setText(String.valueOf(obj_myDb.getFuture_PS_CABGAVR()));
        rfc_cabgavr_fut.setText(String.valueOf(obj_myDb.getFuture_RF_CABGAVR()));

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
