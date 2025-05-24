package cl.ovox.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PUBLICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PublicacionDTO extends UUIDBaseEntity {

    @CreationTimestamp
    @Column(nullable = false, name = "fecha_creacion", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable = false, name = "fecha_modificacion")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PublicacionEstadoDTO estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductoDTO producto;

    @OneToMany
    @JoinColumn(nullable = true)
    private List<OpinionDTO> opiniones;

    @Column(nullable = false)
    private Long calificacion;

}
