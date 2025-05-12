package cl.ecommerce.ecommerce.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PUBLICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PublicacionDTO {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductoDTO producto;

    @Column(nullable = false)
    private Date fecha_publicacion;

    @Column(nullable = false)
    private Integer calificacion;

}
