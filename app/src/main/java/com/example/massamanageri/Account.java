package com.example.massamanageri;

import java.util.ArrayList;

import java.util.List;

public class Account { // Basic datatype for handling account specific data. Contains information about user etc...
    String filename;
    long bmi;
    String name;
    String psswrd;
    double height1;
    double activityGoal;
    WeightPoint tempWeightPoint;
    long yearlyActivity;
    double weeklyactivity;
    List<WeightPoint> weightPointList;


    public Account(String username, String hashedpassword, double weight, double height, double activitygoal) {
        BmiCalculator bmicalc = new BmiCalculator();
        name = username;
        psswrd = hashedpassword;
        bmi = bmicalc.calculateBMI(height, weight);
        height1 = height;
        activityGoal = activitygoal;
        filename = new StringBuilder(username+".txt").toString();
        weightPointList = new ArrayList<WeightPoint>();
        tempWeightPoint = new WeightPoint(weight);
        weightPointList.add(tempWeightPoint);
        yearlyActivity = 0;
        weeklyactivity = 0;
    }

    public String getFilename(){
        return filename;
    }
    public String getUsername(){
        return name;
    }
    public String getPassword(){
        return psswrd;
    }
    public double getHeight(){
        return height1;
    }
    public double getActivityGoal(){return activityGoal;}
    public List<WeightPoint> getWeightPointList(){
        return weightPointList;
    }
    public void updateWeightPointList(List<WeightPoint> l){
        weightPointList = l;
    }

    public void setYearlyActivity(long a) {
        this.yearlyActivity = yearlyActivity+a;
    }
    public void addWeightPoint(WeightPoint w){
        weightPointList.add(w);
    }
    public void updateBMI(double W){
        BmiCalculator bmicalc = new BmiCalculator();
        bmi = bmicalc.calculateBMI(W,height1);

    }
}
