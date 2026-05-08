package utils;

import java.util.regex.Pattern;

public class IvandryUtils {
    private static final Pattern IVANDRY_PATTERN = 
        Pattern.compile("\\bivandry\\b", Pattern.CASE_INSENSITIVE);

    public static boolean isIvandry(String s) {
        return IVANDRY_PATTERN.matcher(s).find();
    }

    public static int getIvandryCount (String... inputs) {
        if (inputs == null || inputs.length == 0) { 
            return 0;
        }

        int count = 0;
        for (String string : inputs) {
            if (isIvandry(string))
                count++;
        }

        return  count;
    }
}
