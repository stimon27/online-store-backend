package org.rimini.hibernate.orm.panache.entities.store.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethod extends PanacheEntity {
    @OneToMany(mappedBy = "shippingMethod", cascade = CascadeType.ALL)
    public List<PurchaseOrderHeader> purchaseOrderHeaders;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String displayLabel;

    @Column(length = L_TEXT_COLUMN_LENGTH)
    public String shippingProvider;

    @Column(nullable = false)
    public Double basePrice;
}
