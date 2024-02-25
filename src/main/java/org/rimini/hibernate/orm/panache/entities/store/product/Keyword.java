package org.rimini.hibernate.orm.panache.entities.store.product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword extends PanacheEntity {
    @ManyToMany(mappedBy = "keywords")
    public List<Product> products;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String keyword;
}
