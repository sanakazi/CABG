package com.example.sanakazi.cabg.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.adapters.DrawerAdapter;
import com.example.sanakazi.cabg.fragments.AboutusFragment;
import com.example.sanakazi.cabg.fragments.DisclaimerFragment;
import com.example.sanakazi.cabg.fragments.PatientInfo1Fragment;
import com.example.sanakazi.cabg.fragments.TermsConditionsFragment;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.FragmentListener {
    View navHeaderLayout;
    private DrawerLayout mDrawerLayout;
    public static ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView  mRecyclerView;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;
    Toolbar toolbar;
    String [] TITLES = {"Begin Risk Assessment" , "About Edwards"," Disclaimer"};
    CharSequence mTitle;
    PatientInfo1Fragment patientInfo1Fragment;
    private static final String PATIENT_INFO1_FRAGMENT= "PATIENT_INFO1_FRAGMENT";

    private static final String TAG=  MainActivity.class.getSimpleName();
    public static final String TERMS_AND_CONDITION= "TERMS_AND_CONDITION";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();
        int which_fragment_to_open= intent.getIntExtra(TERMS_AND_CONDITION,0);



        mRecyclerView = (RecyclerView) findViewById(R.id.navigation_view); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new DrawerAdapter(MainActivity.this,TITLES);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        mDrawerLayout.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State


        if(which_fragment_to_open==1)
        {addTermsConditionsFragment();}
        else {
            addDefaultFragment();
        }

    }


    private void addTermsConditionsFragment() {

        TermsConditionsFragment termsConditionsFragment = (TermsConditionsFragment) getSupportFragmentManager().findFragmentByTag("TermsConditionsFragment");

        if (termsConditionsFragment == null) {
            termsConditionsFragment = new TermsConditionsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, termsConditionsFragment, "TermsConditionsFragment").commit();
        } else {
            replaceFragment(termsConditionsFragment, "TermsConditionsFragment", false);
        }

    }

    private void addDefaultFragment() {

        patientInfo1Fragment = (PatientInfo1Fragment) getSupportFragmentManager().findFragmentByTag(PATIENT_INFO1_FRAGMENT);

        if (patientInfo1Fragment == null) {
            patientInfo1Fragment = new PatientInfo1Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, patientInfo1Fragment, PATIENT_INFO1_FRAGMENT).commit();
        } else {
            replaceFragment(patientInfo1Fragment, PATIENT_INFO1_FRAGMENT, false);
        }

    }

    public  void replaceFragment(Fragment fragment, String tag, boolean addtoBackStack) {
        if (addtoBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).addToBackStack(null).commit();
//            toolbarNAvigationListener();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();
        }
    }

    public void setToolBarTitle(String toolBarTitle) {
        if (toolbar != null) {
//        toolbar.setTitle(toolBarTitle);

            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(toolBarTitle);
            Log.i(TAG, "Toolbar was set");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // mDrawerLayout.closeDrawers();
    }

    @Override
    public void OnFragmentClick(int position) {
        switch(position)
        {
            case 1:
                mDrawerLayout.closeDrawers();
                TermsConditionsFragment termsConditionsFragment = (TermsConditionsFragment) getSupportFragmentManager().findFragmentByTag("termsConditionsFragment");
                if (termsConditionsFragment == null) {
                    termsConditionsFragment = new TermsConditionsFragment();
                }
                replaceFragment(termsConditionsFragment, "termsConditionsFragment", false);

                break;
            case 2:
                mDrawerLayout.closeDrawers();

                AboutusFragment aboutusFragment = (AboutusFragment) getSupportFragmentManager().findFragmentByTag("aboutusFragment");
                if (aboutusFragment == null) {
                    aboutusFragment = new AboutusFragment();
                }
                replaceFragment(aboutusFragment, "aboutusFragment", false);

                break;
            case 3:
                mDrawerLayout.closeDrawers();
                DisclaimerFragment disclaimerFragment = (DisclaimerFragment) getSupportFragmentManager().findFragmentByTag("disclaimerFragment");
                if (disclaimerFragment == null) {
                    disclaimerFragment = new DisclaimerFragment();
                }
                replaceFragment(disclaimerFragment, "disclaimerFragment", false);

                break;
        }
    }


}
