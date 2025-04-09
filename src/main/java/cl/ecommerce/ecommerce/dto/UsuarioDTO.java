package cl.ecommerce.ecommerce.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {

    @Id
    private Integer run;

    @Column
    (nullable = false)
    private Integer dv;

    @Column(nullable=false) 
    private String nombres;

    @Column(nullable=false) 
    private String appaterno;

    @Column(nullable=false) 
    private String apmaterno;

    @Column(nullable=false)  
    private Date fechaNacimiento;

    @Column(nullable=false) 
    private String correo;

    @Column(nullable=false)
    private String hashContrasena;

    //@Column(nullable=false)
    //private Date fechaRegistro;

}
