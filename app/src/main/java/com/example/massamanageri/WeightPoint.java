package com.example.massamanageri;

import java.util.Calendar;
import java.util.Date;

public class WeightPoint{
    double weight;
    long epoch;
    String epochS;

    public WeightPoint(double w){
        weight = w;
        Date date = Calendar.getInstance().getTime();
        epoch = date.getTime();
    }
    public WeightPoint(double w, long e){
        weight = w;
        epoch = e;
    }

    public double getWeight(){
        return weight;
    }

    public long getEpoch(){
        return epoch;
    }

    public String getEpochString() {
        epochS = Long.toString(epoch);
        return epochS;
    }
}
