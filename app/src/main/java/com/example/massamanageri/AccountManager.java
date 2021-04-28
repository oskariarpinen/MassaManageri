package com.example.massamanageri;

import android.content.Context;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

public class AccountManager { // Class to manage the account datastructure.
    PasswordHasher hasher;
    Account account;
    String storedhash, temphash;
    FileReadAndWrite io;
    Context context = null;
    HashMap<String,String> users;

    public AccountManager(Context c) {
        context = c;
    }

    // Method used when creating new account
    public boolean createAccount(String username, String password, String passwordcheck, double weight, double height, long activitygoal) {
        hasher = new PasswordHasher();
        HashMap<String,String> users = new HashMap<String,String>();
        if (users.get(username)!= null){
            return false;
        }
        storedhash = hasher.createHashWithSalt(password);// Calls for hashed pasword from PasswordHasher
        account = new Account(username,storedhash,weight,height,activitygoal);
        io = new FileReadAndWrite();
        io.writeUserFile(account,context);          // FileReadAndWrite creates all neccessary files
        io.writeUserWeightFile(account,context);    // ---.---
        users.put(username,storedhash);             // AccountManager stores the username and
                                                    // hashed password for later use
        return true;
    }

    //Method used when reading user data from file
    public boolean getAccountData(String username){
        io = new FileReadAndWrite();
        List<WeightPoint> list = io.readUserWeightFile(username,context); //Reads users weightfile
        WeightPoint weightpoint = list.get(list.size()-1);
        double w = weightpoint.getWeight(); // Account needs the weight information, hence we pull
                                            // the weight data first
        account = io.readUserFile(username,context,w); //Reads users general file
        account.updateWeightPointList(list);
        if(account != null){
            return true;
        }else{
            return false;
        }
    }
    public Account getAccount(){ //
        return account;
    }
}
