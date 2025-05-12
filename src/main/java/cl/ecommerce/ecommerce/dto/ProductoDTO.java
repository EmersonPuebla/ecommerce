package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.util.List;

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

    @OneToMany
    @JoinColumn(nullable = true)
    private List<ColorDTO> color;
    
    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Integer stock;    
}
