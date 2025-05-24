package cl.ovox.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COLOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String nombre;
}
