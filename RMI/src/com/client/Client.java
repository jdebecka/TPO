package com.client;

import com.ResponseRequest.AddRequest;
import com.ResponseRequest.AddResponse;
import com.ResponseRequest.EchoRequest;
import com.ResponseRequest.EchoResponse;
import com.server.EchoServer;
import com.server.IRemoteAdd;
import com.server.IRemoteEcho;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {

        try {
            String echoURL = "//localhost/rmi/echo";
            String addURL =  "//localhost/rmi/add";

            IRemoteEcho echoServer = (IRemoteEcho) Naming.lookup(echoURL);

            IRemoteAdd remoteAdd = (IRemoteAdd) Naming.lookup(addURL);

            EchoRequest echoRequest = new EchoRequest("I need to hear for you.");
            AddRequest addRequest = new AddRequest(2.6, 13.4);

            AddResponse addResponse = remoteAdd.sendAddRequest(addRequest);
            EchoResponse echoResponse = echoServer.sendMessageRequest(echoRequest);

            System.out.println(echoResponse.getResponseMessage());
            System.out.println(addResponse.getSum());
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
