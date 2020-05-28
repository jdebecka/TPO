package com.ResponseRequest;

import java.io.Serializable;

public class AddRequest implements Serializable {
    static final long serialVersionUID = 42L;
    private double firstDouble;
    private double aDouble;


    public AddRequest(double firstDouble, double aDouble){
        this.firstDouble = firstDouble;
        this.aDouble = aDouble;
    }
    public double getFirstDouble() {
        return firstDouble;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setFirstDouble(double firstDouble) {
        this.firstDouble = firstDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }
}
