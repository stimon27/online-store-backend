package org.rimini.hibernate.orm.panache.entities.store.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.order.PurchaseOrderHeader;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends PanacheEntity {
    @OneToOne(mappedBy = "payment")
    @JsonBackReference
    public PurchaseOrderHeader purchaseOrderHeader;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
            name = "payment_invoice",
            joinColumns = { @JoinColumn(
                    name = "payment_id",
                    referencedColumnName = "id"
            ) },
            inverseJoinColumns = { @JoinColumn(
                    name = "invoice_id",
                    referencedColumnName = "id"
            ) }
    )
    public Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id", nullable = false)
    public PaymentMethod paymentMethod;

    @Column(nullable = false)
    public Boolean invoiceRequested;

    @Column
    public LocalDateTime timestampReceived;
}
