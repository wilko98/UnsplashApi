package com.example.unsplashapi.common;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.unsplashapi.R;

import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    public void putToSP(String key,String value){
        ed.putString(key,value);
    }

    public SharedPreferences getSp() {
        return sp;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        sp=getPreferences(MODE_PRIVATE);
        if (savedInstanceState == null) {
            changeFragment(getFragment());
        }
        ed = sp.edit();

    }

    protected void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    protected int getLayout(){
        return R.layout.ac_container;
    }

    protected abstract Fragment getFragment();

    public void changeFragment(Fragment fragment) {
        boolean addToBackStack = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) != null;
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

}
