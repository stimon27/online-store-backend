package org.rimini.hibernate.orm.panache.entities.store.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends PanacheEntity {
    @OneToOne(mappedBy = "invoice")
    @JsonBackReference
    public Payment payment;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String invoiceNumber;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String filename;
}
