package cl.ovox.ecommerce.model.base;

import java.util.UUID;

import com.github.f4b6a3.uuid.UuidCreator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class UUIDBaseEntity {
    @Id
    @Column(
        name = "id",
        updatable = false,
        nullable = false,
        columnDefinition = "RAW(16)"
    )
    private UUID id;    

    @PrePersist
    protected void generateUUID() {
        if (this.id == null) {
            // Genera un UUIDv7 (Basado en el tiempo)
            this.id = UuidCreator.getTimeOrderedEpoch(); 
        }
    }
}
