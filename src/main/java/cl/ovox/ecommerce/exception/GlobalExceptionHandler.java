package cl.ovox.ecommerce.exception;

import cl.ovox.ecommerce.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Indica que esta clase manejara excepciones globalmente
public class GlobalExceptionHandler {

    // Maneja excepciones de argumentos no validos (errores de validación @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiResponse.badRequest("Error de validación. Por favor, revise los datos");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.error(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(), 
                "BUSINESS_RULE_VIOLATION" 
        );
    }

    // Manejador generico para cualquier otra excepcion no capturada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllUncaughtException(Exception ex) {
        return ApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error inesperado en el servidor",
                "INTERNAL_SERVER_ERROR" 
        );
    }
}