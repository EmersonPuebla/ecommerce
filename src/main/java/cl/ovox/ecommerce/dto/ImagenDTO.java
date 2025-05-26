package cl.ovox.ecommerce.dto;

import cl.ovox.ecommerce.model.base.UUIDBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor; 

@Entity
@Table(name = "IMAGEN")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = true)
public class ImagenDTO extends UUIDBaseEntity{
    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = true)
    private ProductoDTO producto;

    @ManyToOne
    @JoinColumn(name = "opinion_id", nullable = true)
    private OpinionDTO opinion;

    
    public ImagenDTO(String url, ProductoDTO producto) {
        this.url = url;
        this.producto = producto;
        this.opinion = null;
    }

    public ImagenDTO(String url, OpinionDTO opinion) {
         this.url = url;
         this.producto = null;
         this.opinion = opinion;
    }
}