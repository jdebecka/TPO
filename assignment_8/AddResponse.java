import java.io.Serializable;

public class AddResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    long sum;

    AddResponse(long a, long b) {
        sum = a + b;
    }
}