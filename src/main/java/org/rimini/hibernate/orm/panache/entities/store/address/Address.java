package org.rimini.hibernate.orm.panache.entities.store.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.customer.Customer;
import org.rimini.hibernate.orm.panache.entities.store.order.PurchaseOrderHeader;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.S_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends PanacheEntity {
    @OneToOne(mappedBy = "defaultAddress")
    @JsonBackReference
    public Customer owner;

    @OneToMany(mappedBy = "shippingAddress", cascade = CascadeType.ALL)
    public List<PurchaseOrderHeader> shippedOrders;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String addressLine1;

    @Column(length = M_TEXT_COLUMN_LENGTH)
    public String addressLine2;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String city;

    @Column(length = S_TEXT_COLUMN_LENGTH, nullable = false)
    public String zipCode;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String country;
}
