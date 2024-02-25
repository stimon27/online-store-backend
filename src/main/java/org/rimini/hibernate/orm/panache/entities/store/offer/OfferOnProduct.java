package org.rimini.hibernate.orm.panache.entities.store.offer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.product.Product;


import static org.rimini.constant.GlobalStaticData.XL_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferOnProduct extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "specialOffer_id", nullable = false)
    public SpecialOffer specialOffer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product product;

    @Column(nullable = false)
    public Double discount;

    @Column(length = XL_TEXT_COLUMN_LENGTH)
    public String comment;

    @Column(nullable = false)
    public Boolean active;
}
