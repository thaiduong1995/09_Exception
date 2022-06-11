package bai1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAccount extends Exception{
    public ValidateAccount(String message) {
        super(message);
    }

    public static boolean isValidate(String input, String regex) {
        return input.matches(regex);
    }

}
