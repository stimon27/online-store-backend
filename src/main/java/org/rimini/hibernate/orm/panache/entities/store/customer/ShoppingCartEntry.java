package org.rimini.hibernate.orm.panache.entities.store.customer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.product.Product;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntry extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "shoppingCart_id", nullable = false)
    public ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product product;

    @Column(nullable = false)
    public Integer quantity;
}
