package com.server;
import com.ResponseRequest.EchoRequest;
import com.ResponseRequest.EchoResponse;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoServer extends UnicastRemoteObject implements IRemoteEcho {

    public EchoServer() throws RemoteException{
        super();
    }

    @Override
    public EchoResponse sendMessageRequest(EchoRequest echoRequest) throws RemoteException {
        return new EchoResponse("Echo: " + echoRequest.getMessage());
    }
}
