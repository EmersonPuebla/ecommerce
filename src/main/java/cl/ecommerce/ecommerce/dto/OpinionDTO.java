package cl.ecommerce.ecommerce.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    @JoinColumn(nullable = true)
    private List<ImagenDTO> imagen;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private Integer calificacion;

}
