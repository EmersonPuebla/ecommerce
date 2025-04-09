package cl.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORIA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaDTO {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String categoria;

}
