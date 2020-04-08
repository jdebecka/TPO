package Service;

import java.util.Arrays;

public abstract class Functions {

    public static int addIntegers(int[] integersToAdd) {
        return Arrays.stream(integersToAdd).sum();
    }
}
