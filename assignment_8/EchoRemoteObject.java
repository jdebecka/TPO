import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoRemoteObject extends UnicastRemoteObject implements IEcho {
    protected EchoRemoteObject() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public EchoResponse echo(EchoRequest req) throws RemoteException {
        return new EchoResponse("ECHO: " + req.message);
    }

}