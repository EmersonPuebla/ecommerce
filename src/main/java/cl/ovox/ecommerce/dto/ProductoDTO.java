package cl.ovox.ecommerce.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
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
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(nullable = true)
    private ColorDTO color;
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private ImagenDTO imagen;

    @ManyToOne
    @JoinColumn(nullable = true)
    private CategoriaDTO categoria;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Integer stock;    
}
