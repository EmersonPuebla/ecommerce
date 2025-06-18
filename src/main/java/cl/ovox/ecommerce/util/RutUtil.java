package cl.ovox.ecommerce.util;

public class RutUtil {

    public static boolean esRutValido(int run, String dv) {
        int suma = 0;
        int multiplicador = 2;
        int numero = run;

        while (numero > 0) {
            int digito = numero % 10;
            suma += digito * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
            numero /= 10;
        }

        int resto = 11 - (suma % 11);

        String dvCalculado;

        if (resto == 11) {
            dvCalculado = "0";
        } else if (resto == 10) {
            dvCalculado = "K";
        } else {
            dvCalculado = String.valueOf(resto);
        }

        return dvCalculado.equalsIgnoreCase(dv);
    }

    private static void validarFormatoRut(String rutCompleto) {
    if (rutCompleto == null || rutCompleto.trim().isEmpty() || !rutCompleto.matches("^\\d{7,8}-[\\dkK]$")) {
        throw new IllegalArgumentException("Formato de RUT inválido");
    }
    }

    public static int extraerRun(String rutCompleto) {
        validarFormatoRut(rutCompleto);
        String[] partes = rutCompleto.split("-");
        return Integer.parseInt(partes[0].trim());
    }

    public static String extraerDv(String rutCompleto) {
        validarFormatoRut(rutCompleto);
        String[] partes = rutCompleto.split("-");
        return partes[1].trim().toUpperCase();
    }

    public static String rutNormalizado(int run, String dv){
        String rutCompleto = run + "-" + dv;
        return rutCompleto;
    }
}


