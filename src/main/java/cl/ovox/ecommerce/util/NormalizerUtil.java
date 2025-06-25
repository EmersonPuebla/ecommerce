package cl.ovox.ecommerce.util;

import java.util.function.Function;


import cl.ovox.ecommerce.common.Nombreable;

public class NormalizerUtil {

    /**
     * Funcion de Normalizacion de texto
     *
     * @param obj Instancia de una Clase
     * @param normalizer TextUtil::tipo_normalizacion
     * 
     */
    
    public static void normalizeNombre(Nombreable obj, Function<String, String> normalizer) {
        if (obj != null && obj.getNombre() != null && !obj.getNombre().trim().isEmpty() ) {
            obj.setNombre(normalizer.apply(obj.getNombre()));
        }
    }
}
