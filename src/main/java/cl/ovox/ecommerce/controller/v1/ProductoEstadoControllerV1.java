package cl.ovox.ecommerce.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Sigue siendo necesario importar ResponseEntity aquí
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.response.ApiResponse; // Importa tu ApiResponse
import cl.ovox.ecommerce.service.impl.ProductoEstadoImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            return ApiResponse.notFound("No se han encontrado estados");
        }
        
        String mensaje = "Se han encontrado " + estados.size() + " estados";
        return ApiResponse.success(estados, mensaje);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoEstadoDTO>> insert(@RequestBody ProductoEstadoDTO productoEstado) {
        if (productoEstadoService.findByNombre(productoEstado.getNombre()) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "El estado '" + productoEstado.getNombre() + "' ya existe.", "PE-POST-01");
        }

        productoEstadoService.save(productoEstado); 
        return ApiResponse.success(productoEstado, "Se ha insertado exitosamente el estado");
    }
}