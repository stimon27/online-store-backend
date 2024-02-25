package org.rimini.hibernate.orm.panache.entities.store.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart extends PanacheEntity {
    @OneToOne(mappedBy = "shoppingCart")
    @JsonBackReference
    public Customer customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    public List<ShoppingCartEntry> shoppingCartEntries;
}
