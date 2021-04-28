package com.example.massamanageri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfaceActivity extends AppCompatActivity {
    Button weightButton,activityButton,drawButton;
    EditText weightInput,activityInput;
    TextView welcomeText, weightWindow;
    ProgressBar activityProgress;
    String username, temp;
    Double w,a;
    Context context;
    Account account;
    List<WeightPoint> weightPointList = new ArrayList<WeightPoint>();
    WeightPoint tempwp;
    long epoch;
    Date fromepoch;
    String fromepoch2;
    WeightPoint wp;
    CarbonEmissionsAPI api;
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        username = getIntent().getStringExtra("username");

        welcomeText = findViewById(R.id.welcomeMessage);
        temp = "Welcome "+username+"!";
        welcomeText.setText(temp);

        weightButton = findViewById(R.id.weightDatapointButton);
        activityButton = findViewById(R.id.activityDatapointButton);
        weightInput = findViewById(R.id.weightInput);
        activityInput = findViewById(R.id.activityInput);
        weightWindow = findViewById(R.id.weightView);

        context = InterfaceActivity.this;



        AccountManager manager = new AccountManager(context);
        boolean acccountState = manager.getAccountData(username);
        if (acccountState == false){
            loadMainMenu();
        }
        setWeightWindow(manager);

        api = new CarbonEmissionsAPI(context);
        response = api.sendRequest(250);
        System.out.println(response);


        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weightInput.getText()==null){
                    return;
                } else{
                    temp = weightInput.getText().toString();
                    w = Double.valueOf(temp);
                    wp = new WeightPoint(w);
                    weightPointList.add(wp);
                    setWeightWindow(manager);
                    weightInput.setText("");
                }
            }
        });

    }
    public void loadMainMenu(){
        Intent intent = new Intent(InterfaceActivity.this,MainActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void setWeightWindow(AccountManager manager) {
        weightWindow = findViewById(R.id.weightView);
        account = manager.getAccount();
        weightPointList = account.getWeightPointList();
        StringBuilder s = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        for (int i = weightPointList.size()-1; i > weightPointList.size() - 5 || i == 0; i--) {
            tempwp = weightPointList.get(i);
            epoch = tempwp.getEpoch();
            fromepoch = new Date(epoch);
            fromepoch2 = sdf.format(fromepoch);
            w = tempwp.getWeight();
            s.append(fromepoch2).append(": ").append(w).append(" kg\n");
            if (i==0){
                break;
            }
        }
        weightWindow.setText(s);
    }
}