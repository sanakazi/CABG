package com.example.sanakazi.cabg.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.fragments.Fragment2;
import com.example.sanakazi.cabg.fragments.PatientInfo1Fragment;

public class MainActivity extends AppCompatActivity {
    View navHeaderLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    Toolbar toolbar;
    private static final String TAG=  MainActivity.class.getSimpleName();

    CharSequence mTitle;
    PatientInfo1Fragment patientInfo1Fragment;
    Fragment2 fragment2;
    private static final String PATIENT_INFO1_FRAGMENT= "PATIENT_INFO1_FRAGMENT";
    private static final String FRAGMENT2= "FRAGMENT2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mNavigationListview=(ListView)findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBarTitle(getString(R.string.app_name));

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navHeaderLayout = navigationView.getHeaderView(0);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


              //  getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                toolbar.setTitle(getString(R.string.app_name));

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();
                Fragment fragment;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.risk_assessment:
                        toolbar.setTitle(getString(R.string.app_name));
                        return true;
                    case R.id.about:
                        fragment2 = new Fragment2();
                        replaceFragment(fragment2, FRAGMENT2, false);
                        return true;

                    case R.id.disclaimer:

                        return true;
                    default:
                        //Should never come here
                        return true;

                }
            }
        });

//        navigationItems=getResources().getStringArray(R.array.navigation_items);
//        NavigationDrawerAdapter navigationAdapter=new NavigationDrawerAdapter(this,navigationIconsArray,navigationStringsArray);
//        mNavigationListview.setAdapter(navigationAdapter);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the drawer toggle as the DrawerListener

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        addDefaultFragment();
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

    private void replaceFragment(Fragment fragment, String tag, boolean addtoBackStack) {
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
}
