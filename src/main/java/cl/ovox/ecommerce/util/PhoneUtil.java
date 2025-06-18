package cl.ovox.ecommerce.util;

public class PhoneUtil {
    public static boolean isValidPhone(String phone) {
        String[] regexPatterns = {
            "^(\\+?56)?(\\s?)(0?9)(\\s?)[98765432]\\d{7}$", // Regex for Chile
        };

        for (String pattern : regexPatterns) {
            if (phone.matches(pattern)) {
                return true;
            }
        }

        return false;
    }
}