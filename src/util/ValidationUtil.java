package util;

import enums.BookCategory;
import enums.MembershipType;

import java.util.regex.Pattern;

public class ValidationUtil {
    private ValidationUtil(){
    }
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\d{10}$");
    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    public static boolean isPositive(int value){
        return value > 0;
    }
    public static boolean isPositive(double value){
        return value > 0;
    }
    public static boolean isValidEmail(String email){
        if(isNullOrEmpty(email))
            return false;
            return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean isValidPhone(String phone){
        if(isNullOrEmpty(phone))
            return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }
    public static MembershipType parseMembershipType(String input){
        if(isNullOrEmpty(input))
            return null;
        try{
            return MembershipType.valueOf(input.trim().toUpperCase());
        }catch(IllegalArgumentException e){
            return null;
        }
    }
    public static BookCategory parseBookCategory(String input){
        if(isNullOrEmpty(input))
            return null;
        try{
            return BookCategory.valueOf(input.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
