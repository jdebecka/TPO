package com.ResponseRequest;

import java.io.Serializable;

public class EchoResponse implements Serializable {
    static final long serialVersionUID = 42L;

    private String responseMessage;

    public EchoResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
