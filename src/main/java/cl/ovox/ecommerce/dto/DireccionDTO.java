package cl.ovox.ecommerce.dto;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIRECCION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DireccionDTO extends UUIDBaseEntity {
    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String numero;
}
