package com.server;

import com.ResponseRequest.EchoRequest;
import com.ResponseRequest.EchoResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteEcho extends Remote {
    EchoResponse sendMessageRequest(EchoRequest echoRequest) throws RemoteException;
}
