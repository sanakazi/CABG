package com.example.sanakazi.cabg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.others.MyView;

import butterknife.ButterKnife;

/**
 * Created by sanakazi on 11/29/2016.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = new MyView(getActivity());
        ButterKnife.bind(this, view);

        return view;
    }
}
