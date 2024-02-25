package org.rimini.hibernate.orm.panache.entities.store.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.customer.Customer;
import org.rimini.hibernate.orm.panache.entities.store.payment.Payment;
import org.rimini.hibernate.orm.panache.entities.store.address.Address;

import java.time.LocalDateTime;
import java.util.List;

import static org.rimini.constant.GlobalStaticData.XL_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderHeader extends PanacheEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(
            name = "payment_id",
            referencedColumnName = "id",
            nullable = false
    )
    public Payment payment;

    @OneToMany(mappedBy = "purchaseOrderHeader", cascade = CascadeType.ALL)
    public List<PurchaseOrderDetail> purchaseOrderDetails;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "shippingMethod_id", nullable = false)
    public ShippingMethod shippingMethod;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    public Address shippingAddress;

    @Column(nullable = false)
    public LocalDateTime timestamp;

    @Column(length = XL_TEXT_COLUMN_LENGTH)
    public String comment;
}
