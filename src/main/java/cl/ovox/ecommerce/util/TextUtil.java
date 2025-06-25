package cl.ovox.ecommerce.util;
import java.text.Normalizer;

public class TextUtil{

    /**
     * Normalizadores de texto
     *
     * @param input Texto de entrada
     * @return Texto normalizado
     */

    public static String AllUpperCase(String input) {
        if (input == null) return null;
    return input.trim().toUpperCase().replaceAll("\\s+", " ");
    }

    public static String AllLowerCase(String input) {
        if (input == null) return null;
    return input.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    public static String capitalizeWords(String input) {
        if (input == null) return null;

        String cleaned = AllLowerCase(input);

        StringBuilder result = new StringBuilder();
        for (String word : cleaned.split(" ")) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
    }
    return result.toString().trim();
    }

    public static String sanitizeForOracle(String input) {
    if (input == null) return null;

    // 1. Quitar acentos y diacríticos
    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
    String withoutAccents = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    // 2. Reemplazar ñ y Ñ manualmente
    withoutAccents = withoutAccents.replace("ñ", "n").replace("Ñ", "N");

    // 3. Eliminar caracteres no válidos para Oracle (solo letras, números y espacios)
    String sanitized = withoutAccents.replaceAll("[^a-zA-Z0-9\\s]", "");

    // 4. Opcional: normalizar espacios
    return sanitized.trim().replaceAll("\\s+", " ");
    }
}



