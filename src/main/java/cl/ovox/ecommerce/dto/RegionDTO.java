package cl.ovox.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "REGION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String nombre; 
}
