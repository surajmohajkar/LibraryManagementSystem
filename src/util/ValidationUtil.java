package util;

public class ValidationUtil {
    private ValidationUtil(){
    }
    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    public static boolean isPositive(int value){
        return value > 0;
    }
    public static boolean isPositive(double value){
        return value > 0;
    }
}
