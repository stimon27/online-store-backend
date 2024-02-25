package org.rimini.hibernate.orm.panache.entities.store.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ProductSubcategory extends PanacheEntity {
    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    @JsonBackReference
    public List<Product> products;

    @ManyToOne
    @JoinColumn(name = "productCategory_id", nullable = false)
    @JsonManagedReference
    public ProductCategory category;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String name;
}
