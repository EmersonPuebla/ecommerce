package cl.ovox.ecommerce.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.UnidadMedidaDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.UnidadMedidaImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/crud/unidad_medida")

public class UnidadMedidaControllerV1 {

    @Autowired
    private UnidadMedidaImpl unidadMedidaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UnidadMedidaDTO>>> getAll(){
        List<UnidadMedidaDTO> unidadMedida = unidadMedidaService.findAll();

        if (unidadMedida.isEmpty()) {
            return ApiResponse.notFound("No se han encontrado unidades de medida");
        }

        String mensaje = "Se han encontrado " + unidadMedida.size() + " unidades de medida";
        return ApiResponse.success(unidadMedida, mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UnidadMedidaDTO>> getById(@PathVariable Integer id){
        UnidadMedidaDTO unidadMedida = unidadMedidaService.findById(id);
        if (unidadMedida == null) {
            return ApiResponse.notFound("No se encontro la unidad de medida con ID " + id);
        }

        return ApiResponse.success(unidadMedida, "Se ha encontrado la unidad de medida exitosamente.");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UnidadMedidaDTO>> insert(@RequestBody UnidadMedidaDTO unidadMedida){
        if (unidadMedida.getId() != null && unidadMedidaService.findById(unidadMedida.getId()) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "La unidad de medida con ID " + unidadMedida.getId() + " ya existe. Por favor, no proporcione un ID para la creación, o use PUT para actualizar.", "UM-POST-01");
        }

        if (unidadMedidaService.findByNombre(unidadMedida.getNombre()) != null) {
             return ApiResponse.error(HttpStatus.CONFLICT, "La unida de medida con nombre '" + unidadMedida.getNombre() + "' ya existe.", "UM-POST-02");
        }

        if (unidadMedidaService.findBySimbolo(unidadMedida.getSimbolo()) != null) {
             return ApiResponse.error(HttpStatus.CONFLICT, "La unida de medida con simbolo '" + unidadMedida.getSimbolo() + "' ya existe.", "UM-POST-03");
        }

        UnidadMedidaDTO savedUnidadMedida = unidadMedidaService.save(unidadMedida);
  
        return ApiResponse.success(savedUnidadMedida, "Se ha insertado exitosamente la unidad de medida.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UnidadMedidaDTO>> update(@Valid @PathVariable Integer id, @RequestBody UnidadMedidaDTO unidadMedida) {
        if (unidadMedidaService.findById(id) == null) {
            return ApiResponse.notFound("No se ha encontrado la unidad de medida con el ID " + id);
        }
    
        if (unidadMedidaService.update(id, unidadMedida) != null){
            return ApiResponse.success("La unidad de medida ID '" + id +"' se ha actualizado exitosamente");
        }
        // agregar validacion de simbolo
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido actualizar el color por un error interno", "UM-PUT-01");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        if(unidadMedidaService.findById(id) == null){
            return ApiResponse.notFound("No existe una unidad de medida con ID " + id);
        }

        if(unidadMedidaService.delete(id)){
            return ApiResponse.success("La unidad de medida " + id + " se ha eliminado exitosamente.");
        }
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido eliminar la unidad de medida por un error interno", "UM-DEL-02");
    }
}
