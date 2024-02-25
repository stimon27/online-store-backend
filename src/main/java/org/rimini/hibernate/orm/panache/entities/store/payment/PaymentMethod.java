package org.rimini.hibernate.orm.panache.entities.store.payment;

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

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod extends PanacheEntity {
    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    public List<Payment> payments;

    @Column(length = L_TEXT_COLUMN_LENGTH, nullable = false)
    public String name;
}
