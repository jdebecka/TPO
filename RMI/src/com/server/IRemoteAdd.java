package com.server;

import com.ResponseRequest.AddRequest;
import com.ResponseRequest.AddResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteAdd extends Remote {
    AddResponse sendAddRequest(AddRequest addRequest) throws RemoteException;
}
