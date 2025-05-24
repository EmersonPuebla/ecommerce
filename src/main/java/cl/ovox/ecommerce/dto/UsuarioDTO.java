package cl.ovox.ecommerce.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(nullable = false)
    private Integer run;

    @Column(nullable = false, unique = true, columnDefinition = "NUMBER(3)")
    private Integer dv;

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

    @Column(nullable=false) 
    private String correo;

    @JsonIgnore
    @Column(nullable=false)
    private String password;

    @JsonIgnore
    @Column(nullable=false)
    private String salt;
}
