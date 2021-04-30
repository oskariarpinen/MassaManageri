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


// Loggin in is happening in this fragment
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

                // This should have its own class to be honest, but the password is validated
                // here.
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                hasher = new PasswordHasher();
                temppassword1 = readUserFile(username,context);
                temppassword2 = hasher.createHashWithSalt(password);
                if(temppassword1.equals(temppassword2) && fragmentCallback != null) {
                    // Lets you in if you got the password right
                    usernameInput.setText("");
                    passwordInput.setText("");
                    fragmentCallback.loadMainMenu(username);
                }
            }
        });

        // Launches create account fragment
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

    /* Could not figure out how to transfer the AccountManager instance around, had to Jesus tape
     it together. This returns the hashed password from the user file. I really do not know why it
     not in FileReadAndWrite-class
     */
    public String readUserFile(String username, Context context) {
        String filename = username+".txt" ;
        String r = "";
        try {
            InputStream stream = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            r = br.readLine();
            r = br.readLine(); // Finally arrived to the password row, returs hashed password
            stream.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe luettaessa");

        }
        return r;
    }
}
