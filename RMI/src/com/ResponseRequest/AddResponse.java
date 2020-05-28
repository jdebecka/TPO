package com.ResponseRequest;

import java.io.Serializable;

public class AddResponse implements Serializable {
    static final long serialVersionUID = 42L;
    private double sum;

    public AddResponse(double sum){
        this.sum = sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }
}
