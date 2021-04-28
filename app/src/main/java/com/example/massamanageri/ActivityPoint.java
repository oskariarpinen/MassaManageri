package com.example.massamanageri;

import java.util.Calendar;
import java.util.Date;

public class ActivityPoint{
    double activity;
    long epoch;
    Date date;

    public ActivityPoint(double a){
        activity = a;
        date = Calendar.getInstance().getTime();
        epoch = date.getTime();
    }
}
