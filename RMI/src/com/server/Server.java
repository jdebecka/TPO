package com.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {

        String urlEcho =  "//localhost/rmi/echo";
        String urlAdd = "//localhost/rmi/add";

        try {
            LocateRegistry.createRegistry(1099);
            EchoServer echoServer = new EchoServer();
            AddServer addServer = new AddServer();

            Naming.rebind(urlEcho, echoServer);
            Naming.rebind(urlAdd, addServer);

        } catch (RemoteException | MalformedURLException e) {
            System.out.println(e);
        }
    }
}
