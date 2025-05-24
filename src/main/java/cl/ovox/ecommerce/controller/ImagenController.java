package cl.ovox.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.ImagenDTO;
import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.service.impl.ImagenServiceImpl;
import cl.ovox.ecommerce.service.impl.ProductoServiceImpl;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/crud/imagenes")
public class ImagenController {

    /* 
    * NO Deberia haber un GetAllImagenes
    * Enves de aquello debe ser obtener imagen by Producto / Opinion
    */
    @Autowired  
    private ImagenServiceImpl imagenService;

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @GetMapping("/productos/{id_producto}")
    public ResponseEntity<?> getAllByProductoId(@PathVariable Integer id_producto) {
        ProductoDTO producto = productoServiceImpl.findById(id_producto);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        };

        return ResponseEntity.ok(producto.getImagen());
         

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

    @PostMapping
    public ResponseEntity<ImagenDTO> postImagen(@RequestBody ImagenDTO imagen) {
        if (imagenService.findById(imagen.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        ImagenDTO newImagen = imagenService.save(imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(newImagen);
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