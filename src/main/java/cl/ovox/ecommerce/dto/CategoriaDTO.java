package cl.ovox.ecommerce.dto;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoriaDTO extends UUIDBaseEntity {
    
    @Schema(description = "Nombre de la categoría", example = "comida")
    @Column(nullable = false, unique = true)
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre != null ? nombre.toUpperCase() : null;
    }

}
