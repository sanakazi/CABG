package com.example.sanakazi.cabg.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.activities.MainActivity;
import com.example.sanakazi.cabg.activities.PatientInfo2Activity;
import com.example.sanakazi.cabg.custom.RangeSeekBar;
import com.example.sanakazi.cabg.others.MyView;
import com.example.sanakazi.cabg.others.Session;
import com.triggertrap.seekarc.SeekArc;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sanakazi on 11/29/2016.
 */
public class PatientInfo1Fragment extends Fragment{

    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.seekArcProgress1)
    TextView seekArcProgress1;
    @Bind(R.id.seekArc1)
    SeekArc seekArc1;
    @Bind(R.id.cb1) CheckBox cb1; @Bind(R.id.cb2) CheckBox cb2;@Bind(R.id.cb3) CheckBox cb3;@Bind(R.id.cb4) CheckBox cb4;
    @Bind(R.id.rangebar2)
    RangeSeekBar rangebar2;
    @Bind(R.id.min_bp) TextView min_bp; @Bind(R.id.max_bp) TextView max_bp;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.min_age) EditText min_age;   @Bind(R.id.max_age) EditText max_age;
    @Bind(R.id.main_layout) LinearLayout main_layout;
    PatientInfo2Fragment patientInfo2Fragment;
    static int  age=55;
    static int count=55;
    static int minimun_age=50;
    static int maximum_age=80;

  
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_info1, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Patient Info");
        if(Session.coach_marks==0) {
            showCoachMArks();
        }
        events();

        return view;
    }


        private void events(){
          //  seekArc1.setMax(maximum_age-minimun_age);


            seekArcProgress1.setText(String.valueOf(minimun_age));
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (age == 0) {
                        Toast.makeText(getActivity(), "Please select age", Toast.LENGTH_SHORT).show();

                    } else if (!(cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked())) {
                        Toast.makeText(getActivity(), "Please select Class", Toast.LENGTH_SHORT).show();
                    } else {

                     /*   patientInfo2Fragment = new PatientInfo2Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("age", age);
                        bundle.putString("name", et_name.getText().toString());

                        patientInfo2Fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, patientInfo2Fragment).addToBackStack("abc").commit();
                    */

                        Intent intent = new Intent(getActivity(), PatientInfo2Activity.class);
                        intent.putExtra("age", age);
                        intent.putExtra("name", et_name.getText().toString());
                        startActivity(intent);
                    }
                }
            });



            seekArc1.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekArc seekArc) {
                }

                @Override
                public void onStartTrackingTouch(SeekArc seekArc) {
                }

                @Override
                public void onProgressChanged(SeekArc seekArc, int progress,
                                              boolean fromUser) {

                    if(progress<minimun_age) {
                        seekArcProgress1.setText(String.valueOf(progress + minimun_age));
                        age=Integer.parseInt(seekArcProgress1.getText().toString());


                    }
                    else {
                        seekArcProgress1.setText(String.valueOf(progress));
                        age=Integer.parseInt(seekArcProgress1.getText().toString());
                    }

                    //seekArcProgress1.setText(String.valueOf(progress));

                }
            });

            rangebar2.setNotifyWhileDragging(true);
            rangebar2.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                @Override
                public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                    min_bp.setText(String.valueOf(minValue));
                    max_bp.setText(String.valueOf(maxValue));
                }
            });




            cb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb1.setChecked(true);cb2.setChecked(false);cb3.setChecked(false);cb4.setChecked(false);
                }
            });

            cb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb2.setChecked(true);cb1.setChecked(false);cb3.setChecked(false);cb4.setChecked(false);
                }
            });
            cb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb3.setChecked(true);cb1.setChecked(false);cb2.setChecked(false);cb4.setChecked(false);
                }
            });
            cb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb4.setChecked(true);cb1.setChecked(false);cb2.setChecked(false);cb3.setChecked(false);
                }
            });


            main_layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(v);
                    return false;
                }
            });
        }


    private void showCoachMArks()
    {
        seekArc1.setVisibility(View.INVISIBLE);
        rangebar2.setVisibility(View.INVISIBLE);
        Session.coach_marks=1;
        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_coachmark_layout);


        ImageView ok_gotit = (ImageView) dialog.findViewById(R.id.ok_gotit);
        ok_gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                seekArc1.setVisibility(View.VISIBLE);
                rangebar2.setVisibility(View.VISIBLE);
            }
        });

        dialog.show();
    }




    private void showOverLay(){

        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setContentView(R.layout.dialog_coachmark_layout);

        ImageView ok_gotit = (ImageView) dialog.findViewById(R.id.ok_gotit);
        ok_gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
