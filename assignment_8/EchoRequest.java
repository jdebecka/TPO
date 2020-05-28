import java.io.Serializable;

public class EchoRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    EchoRequest(String m) {
        message = m;
    }
}