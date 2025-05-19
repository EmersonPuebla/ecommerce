package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "UNIDAD_MEDIDA")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UnidadMedidaDTO {

    @Id
    private Integer id;

    @Column (nullable=false)
    private String tipo;

    @Column (nullable=false)
    private String simbolo;

}