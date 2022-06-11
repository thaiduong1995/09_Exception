package bai2;

public class ValidatePlayer extends Exception{
    public ValidatePlayer(String message) {
        super(message);
    }

    public static boolean isValidate(String input, String regex) {
        return input.matches(regex);
    }
}
