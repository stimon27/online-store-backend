package org.rimini.hibernate.orm.panache.entities.store.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends PanacheEntity {
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonBackReference
    public List<ProductSubcategory> subcategories;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String name;
}
