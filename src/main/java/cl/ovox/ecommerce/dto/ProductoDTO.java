package cl.ovox.ecommerce.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;

import java.util.ArrayList; // Necesario para inicializar
import java.util.List;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoDTO extends UUIDBaseEntity {
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "El SKU no puede estar vacío")
    @Size(min = 5, max = 50, message = "El SKU debe tener entre 5 y 50 caracteres")
    private String sku;

    @Column(nullable = false)
    @Size(min = 5, max = 60)
    private String nombre;

    @Column(nullable = false)
    @Size(min = 0, max = 4000)
    private String descripcion;

    @ManyToOne()
    @JoinColumn(
        name = "producto_estado_id",
        nullable = false
    )
    private ProductoEstadoDTO estado; 

    @ManyToMany
    @JoinTable(
        name = "PRODUCTO_COLOR",
        joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id")
    )
    private List<ColorDTO> colores = new ArrayList<>(); 
    
    // @OneToMany(mappedBy = "producto")
    // private List<ImagenDTO> imagenes = new ArrayList<>(); 

    @ManyToMany
    @JoinTable(
        name = "PRODUCTO_CATEGORIA",
        joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    )
    private List<CategoriaDTO> categorias = new ArrayList<>(); 
    
    @Column(nullable = false)
    @Min(value = 0)
    private Integer precio;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer stock;    
}