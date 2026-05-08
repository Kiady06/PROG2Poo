package rappel;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class IvandryUtils {

    private static final Pattern IVANDRY_PATTERN = Pattern.compile("\\bivandry\\b", Pattern.CASE_INSENSITIVE);

    private static boolean isIvandry(String input) {
        return IVANDRY_PATTERN.matcher(input).find();
    }

    public static int getIvandryCount(List<String> inputs) {
        if (inputs == null || inputs.isEmpty()) return 0;

        int count = 0;
        for (Object el : inputs) {
            if (el == null || !(el instanceof String)) {
                throw new IllegalArgumentException("Inputs should be strings");
            }
            if (isIvandry((String) el)) count++;
        }
        return count;
    }

    public static int getIvandryCount(String... inputs) {
        if (inputs == null || inputs.length == 0) return 0;
        return getIvandryCount(Arrays.asList(inputs));
    }
}
