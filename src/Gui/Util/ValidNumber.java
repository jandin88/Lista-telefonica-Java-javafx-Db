package Gui.Util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidNumber {

    public static boolean isValidPhoneNumber(String phoneNumber) {

        String pattern = "^(\\+\\d{1,3})?\\d{9,13}$";

        Pattern phonePattern = Pattern.compile(pattern);
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();

    }
}
