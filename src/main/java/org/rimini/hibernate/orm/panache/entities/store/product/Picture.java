package org.rimini.hibernate.orm.panache.entities.store.product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Picture extends PanacheEntity {
    @ManyToMany(mappedBy = "pictures")
    public List<Product> products;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String filename;

    @Column(length = L_TEXT_COLUMN_LENGTH)
    public String alternativeText;
}
