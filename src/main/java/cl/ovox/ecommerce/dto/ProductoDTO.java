package cl.ovox.ecommerce.dto;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    @Id
    private Integer id;

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
