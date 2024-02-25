package org.rimini.hibernate.orm.panache.entities.store.stock;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.product.Product;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockData extends PanacheEntity {
    @OneToOne(mappedBy = "stockData")
    @JsonBackReference
    public Product product;

    @Column(nullable = false)
    public Integer quantity;

    @Column(nullable = false)
    public Boolean refillIncoming;
}
