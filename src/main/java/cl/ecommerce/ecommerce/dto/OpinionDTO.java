package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OPINION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDTO {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private Integer calificacion;

}
