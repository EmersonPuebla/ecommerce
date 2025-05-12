package cl.ecommerce.ecommerce.dto;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private PublicacionEstadoDTO estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductoDTO producto;

    @OneToMany
    @JoinColumn(nullable = true)
    private List<OpinionDTO> opinion;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Integer calificacion;

}
