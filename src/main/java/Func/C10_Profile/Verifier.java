package Func.C10_Profile;

import org.testng.Assert;

public class Verifier {
    public static void verifyAndPrintError(String field, String expected, String actual) {
        if (actual.equals(expected)) {
            System.out.println("PASS - " + field + " matches with the databases!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            System.out.println("---------------------");
        } else {
            System.out.println("FAIL - " + field + " does not match with the database!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            System.out.println("---------------------");
        }
    }
}
