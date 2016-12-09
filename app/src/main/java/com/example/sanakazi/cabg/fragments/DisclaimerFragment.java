package com.example.sanakazi.cabg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.others.AboutUs;
import com.example.sanakazi.cabg.others.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SanaKazi on 12/5/2016.
 */
public class DisclaimerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_disclaimer, container, false);
        getActivity().setTitle("Disclaimer");
        TextView txt_disclaimer = (TextView)view.findViewById(R.id.txt_disclaimer);
        txt_disclaimer.setText(String.valueOf(AboutUs.disclaimer));

        return view;
    }
}
