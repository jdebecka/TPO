import java.io.Serializable;

public class AddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    long first;
    long second;

    AddRequest(long a, long b) {
        first = a;
        second = b;
    }
}