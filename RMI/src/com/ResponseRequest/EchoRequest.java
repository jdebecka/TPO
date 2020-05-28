package com.ResponseRequest;

import java.io.Serializable;

public class EchoRequest implements Serializable {
    static final long serialVersionUID = 42L;
    private String message;

    public String getMessage() {
        return message;
    }

    public EchoRequest(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
