package cl.ovox.ecommerce.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.ProductoEstadoImpl;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/crud/producto_estado")
public class ProductoEstadoControllerV1 {

    @Autowired
    private ProductoEstadoImpl productoEstadoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoEstadoDTO>>> getAll() {
        List<ProductoEstadoDTO> estados = productoEstadoService.findAll();

        if (estados.isEmpty()) {
            return ApiResponse.notFound("No se han encontrado estados de producto.");
        }

        String mensaje = "Se han encontrado " + estados.size() + " estados de producto.";
        return ApiResponse.success(estados, mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoEstadoDTO>> getById(@PathVariable Integer id) {
        ProductoEstadoDTO estado = productoEstadoService.findById(id);

        if (estado == null) {
            return ApiResponse.notFound("No se encontró el estado de producto con ID: " + id);
        }

        return ApiResponse.success(estado, "Se ha encontrado el estado de producto exitosamente.");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoEstadoDTO>> insert(@Valid @RequestBody ProductoEstadoDTO productoEstado) {
        if (productoEstado.getId() != null && productoEstadoService.findById(productoEstado.getId()) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "El estado con ID '" + productoEstado.getId() + "' ya existe. Por favor, no proporcione un ID para la creación, o use PUT para actualizar.", "PE-POST-01");
        }

        if (productoEstadoService.findByNombre(productoEstado.getNombre()) != null) {
             return ApiResponse.error(HttpStatus.CONFLICT, "El estado con nombre '" + productoEstado.getNombre() + "' ya existe.", "PE-POST-02");
        }

        ProductoEstadoDTO savedEstado = productoEstadoService.save(productoEstado);
        // Usar 201 Created para una inserción exitosa
        return ApiResponse.success(savedEstado, "Se ha insertado exitosamente el estado de producto.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoEstadoDTO>> update(@Valid @PathVariable Integer id, @RequestBody ProductoEstadoDTO productoEstado) {
        if (productoEstadoService.findById(id) == null) {
            return ApiResponse.notFound("No se ha encontrado un estado con el ID " + id);
        }

        if (productoEstadoService.update(id, productoEstado) != null) {
            return ApiResponse.success("El estado de producto ID " + id + " se ha actualizado exitosamente.");
        }

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido actualizar el estado de producto por un error interno.", "PE-PUT-01");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        if (productoEstadoService.findById(id) == null) {
            return ApiResponse.notFound("No existe un estado con ID '" + id);
        }

        if (productoEstadoService.delete(id)) {
            return ApiResponse.success("El estado de producto " + id + " se ha eliminado exitosamente.");
        }

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido eliminar el estado de producto por un error interno.", "PE-DEL-02");
    }
}