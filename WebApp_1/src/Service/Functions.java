package Service;
import java.util.Arrays;

//singleton
public final class Functions {

    private Functions() {

    }

    public static int addIntegers(int[] integersToAdd) {
        return Arrays.stream(integersToAdd).sum();
    }

}
