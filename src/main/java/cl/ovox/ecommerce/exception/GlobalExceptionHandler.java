package cl.ovox.ecommerce.exception;

import cl.ovox.ecommerce.response.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja excepciones de argumentos no válidos (errores de validación @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Excluimos el manejo de excepciones si la peticion proviene de Swagger
        if (ex.getMessage().contains("swagger")) {
            return new ResponseEntity<>(HttpStatus.OK);  // Swagger puede recibir OK sin problema
        }
        return ApiResponse.badRequest("Error de validación. Por favor, revise los datos");
    }

    // Manejo de excepciones de IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.error(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                "BUSINESS_RULE_VIOLATION"
        );
    }

    // Manejador genérico para cualquier otra excepción no capturada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllUncaughtException(Exception ex) {
        // Excluimos de nuevo el caso Swagger
        if (ex instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
            return new ResponseEntity<>(HttpStatus.OK);  // Caso de Swagger para peticiones incompletas
        }

        return ApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error inesperado en el servidor",
                "INTERNAL_SERVER_ERROR"
        );
    }
}
