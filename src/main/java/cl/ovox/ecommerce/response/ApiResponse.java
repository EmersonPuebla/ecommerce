package cl.ovox.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 

// Incluye solo los campos no nulos en la respuesta JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiResponse<T> {

    private int status; // Código de estado HTTP
    private String message;
    private String code;
    private T data;

    private ApiResponse(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
    }

    private ApiResponse(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    private ApiResponse(HttpStatus httpStatus, String message, String code) {
        this.status = httpStatus.value();
        this.message = message;
        this.code = code;
    }
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        // Crea una instancia de ApiResponse y la envuelve en ResponseEntity
        ApiResponse<T> apiResponse = new ApiResponse<>(HttpStatus.OK, message, data);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(HttpStatus.OK, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Para errores, ahora recibes el HttpStatus para que puedas devolver el 409, 400, etc.
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus httpStatus, String message, String code) {
        ApiResponse<T> apiResponse = new ApiResponse<>(httpStatus, message, code);
        return new ResponseEntity<>(apiResponse, httpStatus); // El HttpStatus se pasa aqui mismito
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST, message, "VALIDATION_ERROR");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND, message, "RESOURCE_NOT_FOUND");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}