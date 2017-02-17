package com.example.sanakazi.cabg.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.fragments.TermsConditionsFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Summary2Activity extends AppCompatActivity {


    //region Binding values
@Bind(R.id.age_current) TextView age_current; @Bind(R.id.age_future) TextView age_future;
    @Bind(R.id.grad_cur) TextView grad_cur; @Bind(R.id.grad_fut) TextView grad_fut;
    @Bind(R.id.val_cur) TextView val_cur; @Bind(R.id.val_fut) TextView val_fut;
    @Bind(R.id.jet_cur) TextView jet_cur; @Bind(R.id.jet_fut) TextView jet_fut;
    @Bind(R.id.severe_age) TextView severe_age; @Bind(R.id.days_added) TextView days_added;
    @Bind(R.id.btn_share) Button btn_share;
    //endregion

    double gradient_value,valve_area,jet_velocity;
    double new_grad , new_val_area,new_jet_vel;
    int age,chosen_age,daysAdded;
    private static String TAG= Summary2Activity.class.getSimpleName();
    Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary2);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent  = getIntent();
        age = intent.getIntExtra("age",0);
        chosen_age=intent.getIntExtra("chosen_age",0);
        daysAdded=intent.getIntExtra("daysAdded",0);

        gradient_value = intent.getDoubleExtra("gradient",0);
        valve_area = intent.getDoubleExtra("valve_area",0);
        jet_velocity = intent.getDoubleExtra("jet_velocity",0);

        new_grad = intent.getDoubleExtra("new_gradient",0);
        new_val_area = intent.getDoubleExtra("new_valve_area",0);
        new_jet_vel = intent.getDoubleExtra("new_jet_velocity",0);


        Log.w(TAG,age +","+ chosen_age+","+  gradient_value+"," + valve_area +","+ jet_velocity );
        Log.w(TAG,new_grad +","+ new_val_area+","+  new_jet_vel);
        events();

    }

    private void events()
    {
        age_current.setText(String.valueOf(age)); age_future.setText(String.valueOf(chosen_age));
        grad_cur.setText(String.valueOf(gradient_value)); grad_fut.setText(String.valueOf(new_grad));
        val_cur.setText(String.valueOf(String.format("%.2f", valve_area))); val_fut.setText(String.valueOf(String.format("%.2f",new_val_area)));
        jet_cur.setText(String.valueOf(String.format("%.2f",jet_velocity))); jet_fut.setText(String.valueOf(String.format("%.2f",new_jet_vel)));
        int value = chosen_age-age;
        if(value==1){severe_age.setText(String.valueOf( (chosen_age-age)+ " year")); }
        else severe_age.setText(String.valueOf( (chosen_age-age)+ " years"));
        days_added.setText(String.valueOf(daysAdded + " days"));

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               // sendEmail();
                //takeScreenshot();
                View v1 = getWindow().getDecorView().getRootView();
                v1.setDrawingCacheEnabled(true);
                myBitmap = v1.getDrawingCache();
               /* if (Build.VERSION.SDK_INT >= 23) {
                    //do your check here
                    ActivityCompat.requestPermissions(Summary2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    saveBitmap(myBitmap);
                }else
                saveBitmap(myBitmap);*/
                isStoragePermissionGranted();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home : onBackPressed();
                return true;

            case R.id.home_menu: Intent intent = new Intent(Summary2Activity.this,MainActivity.class);
                intent.putExtra(MainActivity.TERMS_AND_CONDITION , 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void saveBitmap(Bitmap bitmap) {

            String filePath = Environment.getExternalStorageDirectory()
                    + File.separator + "Pictures/screenshot.png";
            File imagePath = new File(filePath);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(imagePath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                sendMail_new(filePath);
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            }

    }


    public void sendMail_new(String path) {
        String[] TO = {""};
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,TO);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "Edwards");
     /*   emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                "This is an autogenerated mail from Edward`s app");*/
        emailIntent.setType("image/png");
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        return true;
    }


    public  void  isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.w(TAG,"Permission is granted");
                saveBitmap(myBitmap);
            } else {
                Log.w(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                saveBitmap(myBitmap);
            }
        }
        else {
            //permission is automatically granted on sdk<23 upon installation
            saveBitmap(myBitmap);
        }
    }

}
