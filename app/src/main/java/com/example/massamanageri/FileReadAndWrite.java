package com.example.massamanageri;

import android.content.Context;
import android.icu.util.BuddhistCalendar;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileReadAndWrite {

    private static final String accountDBfilename = "adb.txt";
    Context context = null;
    String filename;
    String s;
    String r;
    String[] x;
    HashMap<String,String> h = null;
    String password;

    double height;
    long activitygoal;
    WeightPoint wp;
    Double weight;
    long time, templong,yearly,weekly;



    public FileReadAndWrite() {
    }

    public List<WeightPoint> readUserWeightFile(String username, Context context) {
        filename = username+"_weight.txt";
        List<WeightPoint> list = new ArrayList<WeightPoint>();
        try {
            InputStream stream = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            r = br.readLine();
            while(r!=null) {
                x = r.split(",");
                weight = Double.valueOf(x[0]);
                time = Long.valueOf(x[1]);
                wp = new WeightPoint(weight,time);
                list.add(wp);
                r = br.readLine();
            }
            stream.close();
        } catch (IOException e) {
            Log.e("IOException", "read error1");

        }
        return list;
    }

    public Account readUserFile(String username, Context context, double w){
        filename = username+".txt";
        InputStream stream = null;

        try {
            stream = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            r = br.readLine();
            r = br.readLine();
            password = r;
            r = br.readLine();
            height = Double.valueOf(r);
            r = br.readLine();
            activitygoal = Long.valueOf(r);
            r = br.readLine();
            yearly = Long.valueOf(r);
            r = br.readLine();
            weekly = Long.valueOf(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account account = new Account(username,password,w,height,activitygoal,yearly,weekly);
        return account;
    }

    public void writeUserFile(Account account, Context context) {
        filename = account.getFilename();
        System.out.println(filename);
        double temp;
        long tempEpoch;
        WeightPoint tempwp;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            s = account.getUsername()+"\n";
            osw.write(s);
            s = account.getPassword()+"\n";
            osw.write(s);
            temp = account.getHeight();
            s = Double.toString(temp);
            s = s+"\n";
            osw.write(s);
            long templong1 = account.getActivityGoal();
            s = Long.toString(templong1);
            s = s+"\n";
            osw.write(s);
            long templong2 = account.getYearlyActivity();
            s = Long.toString(templong2)+"\n";
            osw.write(s);
            long templong3 = account.getWeeklyActivity();
            s = Long.toString(templong3)+"\n";
            osw.write(s);
            osw.close();

        } catch (IOException e) {
            Log.e("IOException", "Write error ");
        }
    }
    public void writeUserWeightFile(Account account, Context context) {
        filename = account.getUsername()+"_weight.txt";
        System.out.println(filename);
        double temp;
        long tempEpoch;
        WeightPoint tempwp;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            List<WeightPoint> h = account.getWeightPointList();
            for(int i = 0;i<h.size();i++){
                tempwp = h.get(i);
                temp = tempwp.getWeight();
                tempEpoch = tempwp.getEpoch();
                s = Double.toString(temp)+","+Long.toString(tempEpoch)+"\n";
                osw.write(s);
            }
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "write error");
        }
    }

    public static HashMap<String, String> readAccountLog(Context context) {
        HashMap<String, String> h = new HashMap<String, String>();
        String[] temp;
        try {
            InputStream stream = context.openFileInput(accountDBfilename);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = br.readLine();
            while (line != null) {
                temp = line.split(",");
                h.put(temp[0], temp[1]);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            Log.e("IOException", "read error2");
        }
        return h;
    }
}
