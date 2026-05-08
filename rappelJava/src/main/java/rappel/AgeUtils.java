package rappel;

import java.util.List;

public class AgeUtils {

    public static int countAdults(int... args) {
        if (args == null || args.length == 0) { return 0; }

        int count = 0;
        for (int age : args) {
            if (age < 0) {
                throw new IllegalArgumentException("Input should only contain positive integers");
            }
            if (age >= 18)
                count++;
        }
        return count;
    }

    public static int countAdults(List<Integer> ages) {
        if (ages == null || ages.isEmpty()) { return 0; }

        int count = 0;
        for (int age : ages) {
            if (age < 0) {
                throw new IllegalArgumentException("Input should only contain positive integers");
            }
            if (age >= 18) count++;
        }
        return count;
    }

}
