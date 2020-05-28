import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");

            EchoRemoteObject echo = new EchoRemoteObject();
            AddRemoteObject add = new AddRemoteObject();

            Naming.bind("//localhost/rmi/echo", echo);
            Naming.bind("//localhost/rmi/add", add);
            
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }
    }
}