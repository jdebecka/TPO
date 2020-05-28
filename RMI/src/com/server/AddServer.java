package com.server;

import com.ResponseRequest.AddRequest;
import com.ResponseRequest.AddResponse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddServer extends UnicastRemoteObject implements IRemoteAdd {
    public AddServer() throws RemoteException {
        super();
    }
    @Override
    public AddResponse sendAddRequest(AddRequest addRequest) throws RemoteException {
        double sum = addRequest.getaDouble() + addRequest.getFirstDouble();
        return new AddResponse(sum);
    }
}
