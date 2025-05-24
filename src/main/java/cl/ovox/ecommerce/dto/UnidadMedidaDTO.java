package cl.ovox.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable=false)
    private String tipo;

    @Column (nullable=false)
    private String simbolo;

}