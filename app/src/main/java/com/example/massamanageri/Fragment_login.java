package com.example.massamanageri;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fragment_login extends Fragment {
    Button loginButton,newAccountButton;
    EditText usernameInput,passwordInput;
    FragmentCallback fragmentCallback;
    String username, password, temppassword1, temppassword2;
    Context context = null;
    boolean login;
    PasswordHasher hasher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        loginButton = view.findViewById(R.id.loginButton);
        newAccountButton = view.findViewById(R.id.createAccountButton);


        usernameInput = view.findViewById(R.id.usernameInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        context = getActivity();
        AccountManager accountManager = new AccountManager(context);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                hasher = new PasswordHasher();
                temppassword1 = readUserFile(username,context);
                temppassword2 = hasher.createHashWithSalt(password);
                System.out.println(temppassword2);
                if(temppassword1.equals(temppassword2) == true && fragmentCallback != null) {
                    fragmentCallback.loadMainMenu(username);
                }
            }
        });

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentCallback!=null){
                    usernameInput.setText("");
                    passwordInput.setText("");
                    fragmentCallback.changeFragmentToCreate();
                }

            }
        });
        return view;
    }
    public void setFragmentCallback(FragmentCallback fragmentCallback){
        this.fragmentCallback = fragmentCallback;
    }
    public String readUserFile(String username, Context context) {
        String filename = username+".txt" ;
        String r = "";
        try {
            InputStream stream = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            r = br.readLine();
            r = br.readLine();
            System.out.println(r);
            stream.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe luettaessa");

        }
        return r;
    }
}
