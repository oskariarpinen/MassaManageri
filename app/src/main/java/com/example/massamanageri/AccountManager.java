package com.example.massamanageri;

import android.content.Context;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

public class AccountManager {
    PasswordHasher hasher;
    Account account;
    String storedhash, temphash;
    FileReadAndWrite io;
    Context context = null;
    HashMap<String,String> users;

    public AccountManager(Context c) {
        context = c;
        io = new FileReadAndWrite();
        users = FileReadAndWrite.readAccountLog(c);
    }


    public boolean createAccount(String username, String password, String passwordcheck, double weight, double height, double activitygoal) {
        hasher = new PasswordHasher();
        if (users.get(username)!= null){
            return false;
        }
        storedhash = hasher.createHashWithSalt(password);
        account = new Account(username,storedhash,weight,height,activitygoal);
        io = new FileReadAndWrite();
        io.writeUserFile(account,context);
        io.writeUserWeightFile(account,context);
        users.put(username,storedhash);
        return true;
    }

    public boolean getAccountData(String username){
        io = new FileReadAndWrite();
        List<WeightPoint> list = io.readUserWeightFile(username,context);
        WeightPoint weightpoint = list.get(list.size()-1);
        double w = weightpoint.getWeight();
        account = io.readUserFile(username,context,w);
        account.updateWeightPointList(list);
        if(account != null){
            return true;
        }else{
            return false;
        }
    }
    public Account getAccount(){
        return account;
    }
}
