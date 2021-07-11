package com.example.accountapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.image1:
                fragment = new Fragment();
                break;
            case R.id.image2:
                fragment = new Fragment();
                break;
            case R.id.image3:
                fragment = new Fragment();
                break;
            case  R.id.image4:
                fragment = new Fragment();
                break;
            default:
                break;
        }
        ft.replace(R.id.fragment, fragment);
        ft.commit();

    }
}
