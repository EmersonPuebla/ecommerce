package cl.ovox.ecommerce.util;

public class EmailUtil {

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        
        return email.matches(regex);
    }
}

