package cl.ovox.ecommerce.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioDTO extends UUIDBaseEntity {

    @Column(nullable = false, unique = true, length = 12)
    private String rut;

    @Column(nullable=false) 
    private String pnombre;

    @Column(nullable=true) 
    private String snombre;

    @Column(nullable=false) 
    private String appaterno;

    @Column(nullable=false) 
    private String apmaterno;

    @Column(nullable=false)  
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @CreationTimestamp
    @Column(nullable = false, name = "fecha_registro", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @Column(nullable = false) 
    private String correo;

    @Column(nullable = false)
    private String telefono;
/* 
    @JsonIgnore
    @Column(nullable=false)
    @NotBlank
    private String password;

    @JsonIgnore
    @Column(nullable=false)
    @NotBlank
    private String salt;
    */
}
