package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESTADO_PUBLICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PublicacionEstadoDTO {
    @Id
    private int id;

    @Column (nullable = false)
    private String estadoPublicacion;

}
