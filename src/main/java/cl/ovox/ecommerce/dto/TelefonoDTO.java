package cl.ovox.ecommerce.dto;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TELEFONO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TelefonoDTO extends UUIDBaseEntity{

    @Column(nullable = false, name = "codigo_pais", length = 5)
    @NotBlank(message = "El codigo del pais no puede estar vacío")
    @Size(min = 1, max = 5, message = "El código del pais debe tener entre 1 y 5 caracteres")
    @Pattern(regexp = "^\\+?[0-9]{1,4}$", message = "El código de país no es válido")
    private String codigoPais;

    @Column(nullable = false, name = "numero_local", length = 15)
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(min = 6, max = 15, message = "El número de teléfono debe tener entre 6 y 15 dígitos")
    @Pattern(regexp = "^[0-9]+$", message = "El número de teléfono solo puede contener dígitos")
    private String numeroLocal;
}
