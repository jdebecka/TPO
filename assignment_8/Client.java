import java.rmi.Naming;

public class Client {
    public static void main(String args[]) {
        try {
            IEcho echo = (IEcho) Naming.lookup("//localhost/rmi/echo");
            EchoResponse resEcho = echo.echo(new EchoRequest("hey there"));
            System.out.println(resEcho.message);

            IAddition add = (IAddition) Naming.lookup("//localhost/rmi/add");
            AddResponse resAdd = add.add(new AddRequest(100, 200));
            System.out.println(resAdd.sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}