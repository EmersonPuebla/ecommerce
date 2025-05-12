package cl.ecommerce.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecommerce.ecommerce.dto.ImagenDTO;
import cl.ecommerce.ecommerce.service.impl.ImagenServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/crud/imagenes")
public class ImagenController {

    /* 
    * NO Deberia haber un GetAllImagenes
    * Enves de aquello debe ser obtener imagen by Producto / Opinion
    */
    @Autowired  
    private ImagenServiceImpl imagenService;

    @GetMapping("/productos/{id_producto}")
    public ResponseEntity<List<ImagenDTO>> getAllByProductoId(@PathVariable Integer id_producto) {
        return null;
    }

    @GetMapping("/opiniones/{id_opinion}")
    public ResponseEntity<List<ImagenDTO>> getAllByOpinionId(@PathVariable Integer id_opinion) {
        return null;
    }

    @GetMapping("/productos/{id_producto}/{index_imagen}")
    public ResponseEntity<ImagenDTO> getByProductoId(@PathVariable Integer id_producto, @PathVariable Integer index_imagen) {
        return null;
    }

    @GetMapping("/opiniones/{id_opinion}/{index_imagen}")
    public ResponseEntity<ImagenDTO> getByOpinionId(@PathVariable Integer id_opinion, @PathVariable Integer index_imagen) {
        return null;
    }

    @PutMapping("/productos/{id_producto}/{index_imagen}")
    public ResponseEntity<ImagenDTO> updateByProductoId(@PathVariable Integer id_producto) {
        return null;   
    }

    @PutMapping("/opiniones/{id_opinion}/{index_imagen}")
    public ResponseEntity<ImagenDTO> updateByOpinionId(@PathVariable Integer id_opinion, @PathVariable Integer index_imagen){
        return null;
    }

    @DeleteMapping("/productos/{id_producto}/{index_imagen}")
    public ResponseEntity<ImagenDTO> deleteById(@PathVariable Integer productoId, @PathVariable Integer index_imagen){
        return null;
    }

    // insert
    //@PostMapping
    //public ResponseEntity<?> insertImagen(@RequestBody )
    

    // @DeleteMapping

}