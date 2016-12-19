

package com.example.sanakazi.cabg.activities;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.fragments.PatientInfo2Fragment;
import com.example.sanakazi.cabg.wheelview.MaterialColor;
import com.example.sanakazi.cabg.wheelview.TextDrawable;
import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PatientSeverityActivity extends AppCompatActivity {

    @Bind(R.id.btn_next) Button btn_next;
    @Bind(R.id.class1) TextView class1;  @Bind(R.id.range1) TextView range1;
    @Bind(R.id.class2) TextView class2;  @Bind(R.id.range2) TextView range2;
    @Bind(R.id.class3) TextView class3;  @Bind(R.id.range3) TextView range3;
    @Bind(R.id.yrs) TextView yrs;
    @Bind(R.id.l1) LinearLayout l1;  @Bind(R.id.l2) LinearLayout l2;   @Bind(R.id.l3) LinearLayout l3;
    private static final int ITEM_COUNT = 31;

    private static String TAG= PatientSeverityActivity.class.getSimpleName();
    Toast toast;

    private int ACT_AGE ;
    private   double ACT_GRAD ;
    private  double ACT_AV ;
    private  double ACT_JV;

    private int diff;
    private static double new_grad,new_av,new_jv;
    static int  chosen_age;
    static int  show_age_msg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_severity);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        ACT_AGE = intent.getIntExtra("age",0);
        ACT_AGE = ACT_AGE-50;
        Log.w(TAG,ACT_AGE + " is current age");
        ACT_GRAD= intent.getDoubleExtra("gradient",0);
        ACT_AV= intent.getDoubleExtra("valve_area",0);
        ACT_JV= intent.getDoubleExtra("jet_velocity",0);
        barColorNew(ACT_GRAD,ACT_AV,ACT_JV);
        events();
        wheelevents();


    }



    //get the materials darker contrast
    private int getContrastColor(Map.Entry<String, Integer> entry) {
        String colorName = MaterialColor.getColorName(entry);
        return MaterialColor.getContrastColor(colorName);
    }


    private void wheelevents()
    {

        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setWheelOffsetX(100);
        wheelView.setSelected(ACT_AGE);



        range1.setText(String.valueOf(ACT_GRAD));
        range2.setText(String.valueOf(ACT_AV));
        range3.setText(String.valueOf(ACT_JV));
        chosen_age=ACT_AGE;



        //create data for the adapter
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(ITEM_COUNT);
        for (int i = 0; i < ITEM_COUNT; i++) {
            Map.Entry<String, Integer> entry = MaterialColor.random(this, "\\D*_500$");
            entries.add(entry);
        }

        //populate the adapter, that knows how to draw each item (as you would do with a ListAdapter)
        wheelView.setAdapter(new MaterialColorAdapter(entries));

        //a listener for receiving a callback for when the item closest to the selection angle changes
        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent, Drawable itemDrawable, int position) {
                //get the item at this position
                Map.Entry<String, Integer> selectedEntry = ((MaterialColorAdapter) parent.getAdapter()).getItem(position);
                parent.setSelectionColor(getContrastColor(selectedEntry));

                if(position<=ACT_AGE)
                {
                    if(show_age_msg==0) {

                        show_age_msg=1;
                     //   wheelView.setWheelDrawableRotatable(false);
                        showAToast("Please select future age");
                      //  Toast.makeText(PatientSeverityActivity.this, "Please select future age", Toast.LENGTH_SHORT).show();

                    }
                    wheelView.setSelected(chosen_age);
                }

                else{
                  //  wheelView.setWheelDrawableRotatable(true);
                    show_age_msg=0;
                    chosen_age=position;
                    severityCalculation(position);
                }
            }
        });

        wheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
            @Override
            public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {
                String msg = String.valueOf(position+50) + " " + isSelected;
               //   Toast.makeText(PatientSeverityActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        //initialise the selection drawable with the first contrast color
        wheelView.setSelectionColor(getContrastColor(entries.get(0)));

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //wheelView.setSelectionAngle(-wheelView.getAngleForPosition(5));
                wheelView.setMidSelected();
            }
        }, 3000); */
    }

    static class MaterialColorAdapter extends WheelArrayAdapter<Map.Entry<String, Integer>> {
        MaterialColorAdapter(List<Map.Entry<String, Integer>> entries) {
            super(entries);
        }

        @Override
        public Drawable getDrawable(int position) {
            Drawable[] drawable = new Drawable[] {
                   createOvalDrawable(getItem(position).getValue()),
                    new TextDrawable(String.valueOf(position+50))
            };
            return new LayerDrawable(drawable);
        }

        private Drawable createOvalDrawable(int color) {
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setColor(color);
            return shapeDrawable;
        }
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

    private void events()
    {

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chosen_age<=ACT_AGE)
                {
                    Toast.makeText(PatientSeverityActivity.this, "Please select future age", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent = new Intent(PatientSeverityActivity.this, Summary1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("age", ACT_AGE+50);
                    intent.putExtra("chosen_age", chosen_age+50);

                    intent.putExtra("gradient", ACT_GRAD);
                    intent.putExtra("valve_area", ACT_AV);
                    intent.putExtra("jet_velocity", ACT_JV);

                    intent.putExtra("new_gradient", new_grad);
                    intent.putExtra("new_valve_area", new_av);
                    intent.putExtra("new_jet_velocity", new_jv);


                    startActivity(intent);
                    Log.w(TAG, "Gradient is " + ACT_GRAD);
                }
            }
        });
    }

    private void severityCalculation(int position)
    {

        diff= position-ACT_AGE;
        new_grad=ACT_GRAD+(diff*5);
        new_av=ACT_AV-(diff*0.1);
        new_jv=ACT_JV+(diff*0.24);

        range1.setText(String.valueOf(String.format("%.2f", new_grad)));
        range2.setText(String.valueOf(String.format("%.2f", new_av)));
        range3.setText(String.valueOf(String.format("%.2f", new_jv)));
        barColor(new_grad,new_av,new_jv);

    }

    private void barColor(double bar1 ,double bar2 , double bar3)
    {
        //region for bar1
        if(bar1<25) {
            l1.setBackgroundColor(getResources().getColor(R.color.mild)); class1.setTextColor(getResources().getColor(R.color.mild));class1.setText("Mild");
        }
        else if(bar1 >=25&&bar1<40)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.moderate)); class1.setTextColor(getResources().getColor(R.color.moderate));class1.setText("Moderate");
        }
        else if(bar1>=40&&bar1<60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.severe)); class1.setTextColor(getResources().getColor(R.color.severe));class1.setText("Severe");
        }
        else if(bar1<=60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.very_severe)); class1.setTextColor(getResources().getColor(R.color.very_severe));class1.setText("Very Severe");
        }

//endregion
        //region for bar2
        if(bar2>1.5) {
            l2.setBackgroundColor(getResources().getColor(R.color.mild)); class2.setTextColor(getResources().getColor(R.color.mild));class2.setText("Mild");
        }
        else if(bar2<=1.5 && bar2>1)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.moderate)); class2.setTextColor(getResources().getColor(R.color.moderate));class2.setText("Moderate");
        }
        else if(bar2<=1 &&bar1 <= -1.0)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.severe)); class2.setTextColor(getResources().getColor(R.color.severe));class2.setText("Severe");
        }
        else if(bar2>-1.0)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.very_severe)); class2.setTextColor(getResources().getColor(R.color.very_severe));class2.setText("Very Severe");
        }
        //endregion
        //region for bar3
        if(bar3<=2.5) {
            l3.setBackgroundColor(getResources().getColor(R.color.mild)); class3.setTextColor(getResources().getColor(R.color.mild));class3.setText("Mild");
        }
        else if(bar3<=-4 && bar3>2.5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.moderate)); class3.setTextColor(getResources().getColor(R.color.moderate));class3.setText("Moderate");
        }
        else if(bar3<=-5 &&bar3 > -4)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.severe)); class3.setTextColor(getResources().getColor(R.color.severe));class3.setText("Severe");
        }
        else if(bar3>-5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.very_severe)); class3.setTextColor(getResources().getColor(R.color.very_severe));class3.setText("Very Severe");
        }
        //endregion
    }


    private void barColorNew(double bar1 ,double bar2 , double bar3)
    {
        //region for bar1
        if(bar1<25) {
            l1.setBackgroundColor(getResources().getColor(R.color.mild)); class1.setTextColor(getResources().getColor(R.color.mild));class1.setText("Mild");
        }
        else if(bar1 >=25&&bar1<40)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.moderate)); class1.setTextColor(getResources().getColor(R.color.moderate));class1.setText("Moderate");
        }
        else if(bar1>=40&&bar1<=60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.severe)); class1.setTextColor(getResources().getColor(R.color.severe));class1.setText("Severe");
        }
        else if(bar1>60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.very_severe)); class1.setTextColor(getResources().getColor(R.color.very_severe));class1.setText("Very Severe");
        }

//endregion
        //region for bar2
        if(bar2>1.5) {
            l2.setBackgroundColor(getResources().getColor(R.color.mild)); class2.setTextColor(getResources().getColor(R.color.mild));class2.setText("Mild");
        }
        else if(bar2<=1.5 && bar2>=1)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.moderate)); class2.setTextColor(getResources().getColor(R.color.moderate));class2.setText("Moderate");
        }
        else if(bar2<=1 &&bar1 >=-0.8)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.severe)); class2.setTextColor(getResources().getColor(R.color.severe));class2.setText("Severe");
        }
        else if(bar2<-0.8)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.very_severe)); class2.setTextColor(getResources().getColor(R.color.very_severe));class2.setText("Very Severe");
        }
        //endregion
        //region for bar3
        if(bar3<2.5) {
            l3.setBackgroundColor(getResources().getColor(R.color.mild)); class3.setTextColor(getResources().getColor(R.color.mild));class3.setText("Mild");
        }
        else if(bar3<=4 && bar3>=2.5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.moderate)); class3.setTextColor(getResources().getColor(R.color.moderate));class3.setText("Moderate");
        }
        else if(bar3<=-5 &&bar3 >= -4)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.severe)); class3.setTextColor(getResources().getColor(R.color.severe));class3.setText("Severe");
        }
        else if(bar3>-5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.very_severe)); class3.setTextColor(getResources().getColor(R.color.very_severe));class3.setText("Very Severe");
        }
        //endregion
    }

    public void showAToast (String st){ //"Toast toast" is declared in the class
        try{ toast.getView().isShown();     // true if visible
            toast.setText(st);
        } catch (Exception e) {         // invisible if exception
            toast = Toast.makeText(PatientSeverityActivity.this, st, Toast.LENGTH_SHORT);
        }
        toast.show();  //finally display it
    }

}

















/*

package com.example.sanakazi.cabg.activities;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.fragments.PatientInfo2Fragment;
import com.example.sanakazi.cabg.wheelview.MaterialColor;
import com.example.sanakazi.cabg.wheelview.TextDrawable;
import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PatientSeverityActivity extends AppCompatActivity {

    @Bind(R.id.btn_next) Button btn_next;
    @Bind(R.id.class1) TextView class1;  @Bind(R.id.range1) TextView range1;
    @Bind(R.id.class2) TextView class2;  @Bind(R.id.range2) TextView range2;
    @Bind(R.id.class3) TextView class3;  @Bind(R.id.range3) TextView range3;
    @Bind(R.id.l1) LinearLayout l1;  @Bind(R.id.l2) LinearLayout l2;   @Bind(R.id.l3) LinearLayout l3;
    private static final int ITEM_COUNT = 31;

    private static String TAG= PatientSeverityActivity.class.getSimpleName();
    Toast toast;

    private int ACT_AGE;
    private   double ACT_GRAD ;
    private  double ACT_AV ;
    private  double ACT_JV;

   private int diff;
    private static double new_grad,new_av,new_jv;
    static int  chosen_age;
    static int  show_age_msg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_severity);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        ACT_AGE = intent.getIntExtra("age",0);
        ACT_AGE = ACT_AGE-50;
        Log.w(TAG,ACT_AGE + " is current age");
        ACT_GRAD= intent.getDoubleExtra("gradient",0);
        ACT_AV= intent.getDoubleExtra("valve_area",0);
        ACT_JV= intent.getDoubleExtra("jet_velocity",0);
        barColorNew(ACT_GRAD,ACT_AV,ACT_JV);
        events();
        wheelevents();


    }



    //get the materials darker contrast
    private int getContrastColor(Map.Entry<String, Integer> entry) {
        String colorName = MaterialColor.getColorName(entry);
        return MaterialColor.getContrastColor(colorName);
    }


    private void wheelevents()
    {

        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setWheelOffsetX(100);
        wheelView.setSelected(ACT_AGE);



        range1.setText(String.valueOf(ACT_GRAD));
        range2.setText(String.valueOf(ACT_AV));
        range3.setText(String.valueOf(ACT_JV));
        chosen_age=ACT_AGE;



        //create data for the adapter
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(ITEM_COUNT);
        for (int i = 0; i < ITEM_COUNT; i++) {
            Map.Entry<String, Integer> entry = MaterialColor.random(this, "\\D*_500$");
            entries.add(entry);
        }

        //populate the adapter, that knows how to draw each item (as you would do with a ListAdapter)
        wheelView.setAdapter(new MaterialColorAdapter(entries));

        //a listener for receiving a callback for when the item closest to the selection angle changes
        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent, Drawable itemDrawable, int position) {
                //get the item at this position
                Map.Entry<String, Integer> selectedEntry = ((MaterialColorAdapter) parent.getAdapter()).getItem(position);
                parent.setSelectionColor(getContrastColor(selectedEntry));
                Log.w(TAG, "PSOTION = " +position + " act age = " + ACT_AGE + "chosen_age = " + chosen_age);
                if(position<=ACT_AGE)
                {
                    if(show_age_msg==0) {

                        show_age_msg=1;
                        wheelView.setWheelDrawableRotatable(false);
                        showAToast("Please select future age");
                       // Toast.makeText(PatientSeverityActivity.this, "Please select future age", Toast.LENGTH_SHORT).show();

                    }
                    wheelView.setSelected(chosen_age);
                }
                else{
                    wheelView.setWheelDrawableRotatable(true);
                    show_age_msg=0;
                    chosen_age=position;
                    severityCalculation(position);
                }
            }
        });

        wheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
            @Override
            public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {
                String msg = String.valueOf(position) + " " + isSelected;
                Toast.makeText(PatientSeverityActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        //initialise the selection drawable with the first contrast color
        wheelView.setSelectionColor(getContrastColor(entries.get(0)));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //wheelView.setSelectionAngle(-wheelView.getAngleForPosition(5));
                wheelView.setMidSelected();
            }
        }, 3000);

    }

    static class MaterialColorAdapter extends WheelArrayAdapter<Map.Entry<String, Integer>> {
        MaterialColorAdapter(List<Map.Entry<String, Integer>> entries) {
            super(entries);
        }

        @Override
        public Drawable getDrawable(int position) {
            Drawable[] drawable = new Drawable[] {
                    createOvalDrawable(getItem(position).getValue()),
                    new TextDrawable(String.valueOf(position+50))
            };
            return new LayerDrawable(drawable);
        }

        private Drawable createOvalDrawable(int color) {
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setColor(color);
            return shapeDrawable;
        }
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

    private void events()
    {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chosen_age<=ACT_AGE)
                {
                    Toast.makeText(PatientSeverityActivity.this, "Please select future age", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent = new Intent(PatientSeverityActivity.this, Summary1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("age", ACT_AGE+50);
                    intent.putExtra("chosen_age", chosen_age+50);

                    intent.putExtra("gradient", ACT_GRAD);
                    intent.putExtra("valve_area", ACT_AV);
                    intent.putExtra("jet_velocity", ACT_JV);

                    intent.putExtra("new_gradient", new_grad);
                    intent.putExtra("new_valve_area", new_av);
                    intent.putExtra("new_jet_velocity", new_jv);


                    startActivity(intent);
                    Log.w(TAG, "Gradient is " + ACT_GRAD);
                }
            }
        });
    }

    private void severityCalculation(int position)
    {


        diff= position-ACT_AGE;
        Log.w(TAG,"new age= " + position + " actual age= "+ACT_AGE + " DIFF= "+diff);
        new_grad=ACT_GRAD+(diff*5);
        new_av=ACT_AV-(diff*0.1);
        new_jv=ACT_JV+(diff*0.24);

        Log.w(TAG, "act grad = "+ACT_GRAD + "new grad = " + new_grad);

        range1.setText(String.valueOf(String.format("%.2f", new_grad)));
        range2.setText(String.valueOf(String.format("%.2f", new_av)));
        range3.setText(String.valueOf(String.format("%.2f", new_jv)));
        barColor(new_grad,new_av,new_jv);

    }

    private void barColor(double bar1 ,double bar2 , double bar3)
    {
       //region for bar1
        if(bar1<25) {
            l1.setBackgroundColor(getResources().getColor(R.color.mild)); class1.setTextColor(getResources().getColor(R.color.mild));class1.setText("Mild");
        }
        else if(bar1 >=25&&bar1<40)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.moderate)); class1.setTextColor(getResources().getColor(R.color.moderate));class1.setText("Moderate");
        }
        else if(bar1>=40&&bar1<60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.severe)); class1.setTextColor(getResources().getColor(R.color.severe));class1.setText("Severe");
        }
        else if(bar1<=60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.very_severe)); class1.setTextColor(getResources().getColor(R.color.very_severe));class1.setText("Very Severe");
        }

//endregion
        //region for bar2
        if(bar2>1.5) {
            l2.setBackgroundColor(getResources().getColor(R.color.mild)); class2.setTextColor(getResources().getColor(R.color.mild));class2.setText("Mild");
        }
        else if(bar2<=1.5 && bar2>1)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.moderate)); class2.setTextColor(getResources().getColor(R.color.moderate));class2.setText("Moderate");
        }
        else if(bar2<=1 &&bar1 <= -1.0)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.severe)); class2.setTextColor(getResources().getColor(R.color.severe));class2.setText("Severe");
        }
        else if(bar2>-1.0)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.very_severe)); class2.setTextColor(getResources().getColor(R.color.very_severe));class2.setText("Very Severe");
        }
       //endregion
        //region for bar3
        if(bar3<=2.5) {
            l3.setBackgroundColor(getResources().getColor(R.color.mild)); class3.setTextColor(getResources().getColor(R.color.mild));class3.setText("Mild");
        }
        else if(bar3<=-4 && bar3>2.5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.moderate)); class3.setTextColor(getResources().getColor(R.color.moderate));class3.setText("Moderate");
        }
        else if(bar3<=-5 &&bar3 > -4)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.severe)); class3.setTextColor(getResources().getColor(R.color.severe));class3.setText("Severe");
        }
        else if(bar3>-5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.very_severe)); class3.setTextColor(getResources().getColor(R.color.very_severe));class3.setText("Very Severe");
        }
        //endregion
    }


    private void barColorNew(double bar1 ,double bar2 , double bar3)
    {
        //region for bar1
        if(bar1<25) {
            l1.setBackgroundColor(getResources().getColor(R.color.mild)); class1.setTextColor(getResources().getColor(R.color.mild));class1.setText("Mild");
        }
        else if(bar1 >=25&&bar1<40)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.moderate)); class1.setTextColor(getResources().getColor(R.color.moderate));class1.setText("Moderate");
        }
        else if(bar1>=40&&bar1<=60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.severe)); class1.setTextColor(getResources().getColor(R.color.severe));class1.setText("Severe");
        }
        else if(bar1>60)
        {
            l1.setBackgroundColor(getResources().getColor(R.color.very_severe)); class1.setTextColor(getResources().getColor(R.color.very_severe));class1.setText("Very Severe");
        }

//endregion
        //region for bar2
        if(bar2>1.5) {
            l2.setBackgroundColor(getResources().getColor(R.color.mild)); class2.setTextColor(getResources().getColor(R.color.mild));class2.setText("Mild");
        }
        else if(bar2<=1.5 && bar2>=1)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.moderate)); class2.setTextColor(getResources().getColor(R.color.moderate));class2.setText("Moderate");
        }
        else if(bar2<=1 &&bar1 >=-0.8)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.severe)); class2.setTextColor(getResources().getColor(R.color.severe));class2.setText("Severe");
        }
        else if(bar2<-0.8)
        {
            l2.setBackgroundColor(getResources().getColor(R.color.very_severe)); class2.setTextColor(getResources().getColor(R.color.very_severe));class2.setText("Very Severe");
        }
        //endregion
        //region for bar3
        if(bar3<2.5) {
            l3.setBackgroundColor(getResources().getColor(R.color.mild)); class3.setTextColor(getResources().getColor(R.color.mild));class3.setText("Mild");
        }
        else if(bar3<=4 && bar3>=2.5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.moderate)); class3.setTextColor(getResources().getColor(R.color.moderate));class3.setText("Moderate");
        }
        else if(bar3<=-5 &&bar3 >= -4)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.severe)); class3.setTextColor(getResources().getColor(R.color.severe));class3.setText("Severe");
        }
        else if(bar3>-5)
        {
            l3.setBackgroundColor(getResources().getColor(R.color.very_severe)); class3.setTextColor(getResources().getColor(R.color.very_severe));class3.setText("Very Severe");
        }
        //endregion
    }

    public void showAToast (String st){ //"Toast toast" is declared in the class
        try{ toast.getView().isShown();     // true if visible
            toast.setText(st);
        } catch (Exception e) {         // invisible if exception
            toast = Toast.makeText(PatientSeverityActivity.this, st, Toast.LENGTH_SHORT);
        }
        toast.show();  //finally display it
    }


    }

*/
