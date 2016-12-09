package com.example.sanakazi.cabg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.adapters.GenreAdapter;
import com.example.sanakazi.cabg.expandableview.GenreDataFactory;
import com.example.sanakazi.cabg.others.AboutUs;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SanaKazi on 12/5/2016.
 */
public class AboutusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_about_us, container, false);
        getActivity().setTitle("About Edwards");
        TextView txt1 = (TextView)view.findViewById(R.id.txt1) ;
        txt1.setText(String.valueOf(AboutUs.intro));

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        GenreAdapter adapter = new GenreAdapter(GenreDataFactory.makeGenres());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
