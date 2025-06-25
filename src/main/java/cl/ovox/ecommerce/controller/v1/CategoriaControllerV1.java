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

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/api/v1/crud/categorias")
public class CategoriaControllerV1 {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Obtener todas las categorías", description = "Retorna una lista con todas las categorías disponibles", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de categorías encontrada", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    // Aquí asumo que ApiResponse es tu clase personalizada que envuelve
                    // List<CategoriaDTO>
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No existen categorías disponibles", content = @io.swagger.v3.oas.annotations.media.Content)
    })
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> getAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();

        if (categorias.isEmpty()) {
            return ApiResponse.error(HttpStatus.NOT_FOUND, "No existen categorias disponibles.", "C-GET-01");
        }
        return ApiResponse.success(categorias, "Se han encontrado " + categorias.size() + " categorias.");
    }

    @GetMapping("/{nombre}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Obtener categoría por nombre", description = "Obtiene una categoria en base a su nombre", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría encontrada", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<ApiResponse<CategoriaDTO>> getByNombre(@PathVariable String nombre) {
        CategoriaDTO categoria = categoriaService.findByNombre(nombre);

        if (categoria == null) {
            return ApiResponse.error(HttpStatus.NOT_FOUND, "No existe la categoria ingresada.", "C-GET-NAME-01");
        }

        return ApiResponse.success(categoria,
                "Se han encontrado la categoria " + categoria.getNombre() + " exitosamente.");
    }

    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Insertar nueva categoría", description = "Crea una nueva categoría si no existe otra con el mismo nombre", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la categoría a crear", required = true, content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CategoriaDTO.class))), responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Categoría creada exitosamente", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "La categoría ya existe", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<CategoriaDTO>> insertarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = categoriaService.save(categoriaDTO); // Método de servicio para guardar la
                                                                           // categoría

        if (nuevaCategoria == null) {
            return ApiResponse.error(
                    HttpStatus.CONFLICT,
                    "La categoría ya existe.",
                    "C-POST-CAT-01");
        }

        return ApiResponse.success(
                nuevaCategoria,
                 "Categoría creada exitosamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{nombre}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Actualizar una categoría", description = "Actualiza una categoría existente identificada por su nombre", parameters = {
            @io.swagger.v3.oas.annotations.Parameter(name = "nombre", description = "Nombre de la categoría a actualizar", required = true)
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos nuevos de la categoría", required = true, content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CategoriaDTO.class))), responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ApiResponse<CategoriaDTO>> update(@PathVariable String nombre,
            @RequestBody CategoriaDTO categoria) {
        if (categoriaService.findByNombre(nombre) == null) {
            return ApiResponse.notFound("No se encontro la categoria " + nombre);
        }

        CategoriaDTO newCategoria = categoriaService.update(nombre, categoria);

        if (newCategoria != null) {
            return ApiResponse.success(newCategoria, "Categoria actualizada exitosamente.");
        }

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "La categoria no ha podido ser actualizada.",
                "C-PUT-01");
    }

    @DeleteMapping("/{nombre}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Eliminar una categoría", description = "Elimina una categoría por su nombre si existe", parameters = {
            @io.swagger.v3.oas.annotations.Parameter(name = "nombre", description = "Nombre de la categoría a eliminar", required = true)
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error al intentar eliminar la categoría", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String nombre) {
        CategoriaDTO categoria = categoriaService.findByNombre(nombre);

        if (categoria == null) {
            return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se logró eliminar la categoría.",
                    "C-DELETE-01");
        }

        categoriaService.delete(nombre);
        return ApiResponse.success("La categoría fue eliminada exitosamente.");
    }

}
