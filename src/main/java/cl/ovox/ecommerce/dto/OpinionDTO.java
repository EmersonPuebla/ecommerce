package cl.ovox.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.ArrayList; // Necesario para inicializar
import java.util.List;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OPINION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpinionDTO extends UUIDBaseEntity{

    // ¡¡CORRECCIÓN CLAVE AQUÍ!! mappedBy debe ser "opinion"
    @OneToMany(mappedBy = "opinion") 
    private List<ImagenDTO> imagenes = new ArrayList<>(); // ¡Inicializado!

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    // Es necesario usar @Valid en el controller para que maneje esta validacion correctamente
    @Min(value = 1, message = "La calificación debe ser al menos 1")
    @Max(value = 5, message = "La calificación no puede exceder 5")
    private Integer calificacion;

}