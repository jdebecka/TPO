import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddRemoteObject extends UnicastRemoteObject implements IAddition {
    protected AddRemoteObject() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public AddResponse add(AddRequest req) throws RemoteException {
        return new AddResponse(req.first, req.second);
    }

}