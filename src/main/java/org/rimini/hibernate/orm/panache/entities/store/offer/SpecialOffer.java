package org.rimini.hibernate.orm.panache.entities.store.offer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.XL_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialOffer extends PanacheEntity {
    @OneToMany(mappedBy = "specialOffer", cascade = CascadeType.ALL)
    public List<OfferOnProduct> offers;

    @Column(length = L_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String name;

    @Column(nullable = false)
    public LocalDateTime timestampFrom;

    @Column
    public LocalDateTime timestampTo;

    @Column(length = XL_TEXT_COLUMN_LENGTH)
    public String description;
}
