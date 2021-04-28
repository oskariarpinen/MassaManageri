package com.example.massamanageri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentCallback {
    Fragment fragment;
    FragmentManager manager;
    FragmentTransaction transaction;
    Context context = null;
    CarbonEmissionsAPI api;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spawnLoginFragment();
        context = MainActivity.this;

    }

    public void spawnCreateAccountFragment(){
        Fragment_createaccount fragment = new Fragment_createaccount();
        fragment.setFragmentCallback(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentWindow,fragment);
        transaction.commit();

    }
    public void spawnLoginFragment(){
        Fragment_login fragment = new Fragment_login();
        fragment.setFragmentCallback(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentWindow,fragment);
        transaction.commit();

    }

    @Override
    public void changeFragmentToCreate() {
        spawnCreateAccountFragment();
    }

    @Override
    public void loadMainMenu (String u){
        Intent intent = new Intent(MainActivity.this,InterfaceActivity.class);
        username = u;
        intent.putExtra("username",username);
        startActivity(intent);
    }
    @Override
    public void loadLoginFragment(){
        spawnLoginFragment();
    }

    public Context getContext(){
        context = MainActivity.this;
        return context;
    }

}