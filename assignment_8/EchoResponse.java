import java.io.Serializable;

public class EchoResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    EchoResponse(String m) {
        message = m;
    }
}