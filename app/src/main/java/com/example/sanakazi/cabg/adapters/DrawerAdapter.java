package com.example.sanakazi.cabg.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanakazi.cabg.R;

/**
 * Created by SanaKazi on 12/5/2016.
 */


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    Context context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mNavTitles[];
    public FragmentListener fragmentListener;



    public interface FragmentListener{
        void OnFragmentClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;



        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                Holderid = 1;
            }
            else{
                Holderid = 0;
            }
        }


    }



    public DrawerAdapter(Context context , String Titles[]){

        mNavTitles = Titles;
        this.context = context;
        fragmentListener= (FragmentListener)context;

    }


    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item,parent,false);
            ViewHolder vhItem = new ViewHolder(v,viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header,parent,false);

            ViewHolder vhHeader = new ViewHolder(v,viewType);
            return vhHeader;


        }
        return null;

    }


    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, final int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentListener.OnFragmentClick(position);

                }
            });

        }
        else{

        }
    }


    @Override
    public int getItemCount() {

        return mNavTitles.length+1;
    }



    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


}