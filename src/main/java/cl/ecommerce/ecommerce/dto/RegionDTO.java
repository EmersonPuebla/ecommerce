package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "region") 
    private List<ComunaDTO> comunas;  
}
