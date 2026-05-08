package utils;

public class AgeUtils {
    public static  int countAdults(int... args) {
        if (args == null || args.length == 0) {
            return 0;
        }

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

    // public static void main(String[] args) {
    //     // Test Case 1: Standard mix of adults and minors
    //     System.out.println("Test 1 (Mixed ages): " + countAdults(12, 18, 30, 15, 22)); 
    //     // Expected output: 3

    //     // Test Case 2: Only minors
    //     System.out.println("Test 2 (Only minors): " + countAdults(5, 10, 17)); 
    //     // Expected output: 0

    //     // Test Case 3: Only adults
    //     System.out.println("Test 3 (Only adults): " + countAdults(25, 40, 18)); 
    //     // Expected output: 3

    //     // Test Case 4: Empty input
    //     System.out.println("Test 4 (Empty input): " + countAdults()); 
    //     // Expected output: 0

    //     // Test Case 5: Testing exception handling
    //     try {
    //         System.out.println("Test 5 (Negative value check): ");
    //         countAdults(20, -5, 35);
    //     } catch (IllegalArgumentException e) {
    //         System.out.println("Caught expected exception: " + e.getMessage());
    //     }
    // }
}
