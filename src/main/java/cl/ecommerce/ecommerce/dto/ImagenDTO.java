package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IMAGEN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenDTO {
    @Id
    private String id;

    @Column(nullable = false)
    private String url;
    
}
