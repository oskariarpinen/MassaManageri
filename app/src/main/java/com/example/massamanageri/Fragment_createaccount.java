package com.example.massamanageri;

import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.accounts.AccountManager.*;


// Account creating is happening in this fragment
public class Fragment_createaccount extends Fragment {

    Button loginButton,newAccountButton;
    EditText usernameInput,passwordInput, passwordcheckInput,weightInput,heightInput,activitygoalInput;
    String username, password, passwordcheck;
    double weight, height;
    long activitygoal;
    FragmentCallback fragmentCallback;
    TextView userOut;
    Boolean accountState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createaccount_fragment,container,false);
        loginButton = view.findViewById(R.id.loginButton);
        newAccountButton = view.findViewById(R.id.createAccountButton);

        usernameInput = view.findViewById(R.id.usernameInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        passwordcheckInput = view.findViewById(R.id.passwordcheckInput);
        weightInput = view.findViewById(R.id.startWeightInput);
        heightInput = view.findViewById(R.id.heightInput);
        activitygoalInput = view.findViewById(R.id.activityGoalInput);
        userOut = view.findViewById(R.id.userOutput);



        Context context = getActivity();
        com.example.massamanageri.AccountManager accountmanager = new com.example.massamanageri.AccountManager(context);

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                passwordcheck = passwordcheckInput.getText().toString();
                weight = Double.parseDouble(weightInput.getText().toString());
                height = Double.parseDouble(heightInput.getText().toString());
                activitygoal = Long.parseLong(activitygoalInput.getText().toString());

                // Valiting password generally before sending it off.
                if(!password.equals(passwordcheck)){
                    userOut.setText("Passwords dont match.");
                }else if(password.length() < 12){
                    userOut.setText("Password too short. (12 chars minimum)");
                }else{
                    // Creates account if everything is cool
                    accountState = accountmanager.createAccount(username,password,passwordcheck,weight,height,activitygoal);
                    if (!accountState){
                        userOut.setText("Username already in use");
                    } else {
                        fragmentCallback.loadLoginFragment();
                    }
            }}
        });
        return view;
    }
    //Fragment callback to switch between fragments from a fragment
    public void setFragmentCallback(FragmentCallback fragmentCallback){
        this.fragmentCallback = fragmentCallback;
    }
}
