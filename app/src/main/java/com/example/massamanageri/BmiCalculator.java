package com.example.massamanageri;

import static java.lang.Math.sqrt;

public class BmiCalculator {

    public BmiCalculator(){

    }

    public long calculateBMI(double w, double h){
        double tmpbmi;
        long bmi;
        tmpbmi = w/sqrt(h);
        bmi = Math.round(tmpbmi);
        return bmi;
    }

}
