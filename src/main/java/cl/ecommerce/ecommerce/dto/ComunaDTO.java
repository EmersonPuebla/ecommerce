package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMUNA")
public class ComunaDTO {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String nombre; 

    @ManyToOne
    private RegionDTO region;  
}
