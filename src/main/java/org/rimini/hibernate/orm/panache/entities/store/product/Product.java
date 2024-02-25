package org.rimini.hibernate.orm.panache.entities.store.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.customer.ShoppingCartEntry;
import org.rimini.hibernate.orm.panache.entities.store.offer.OfferOnProduct;
import org.rimini.hibernate.orm.panache.entities.store.order.PurchaseOrderDetail;
import org.rimini.hibernate.orm.panache.entities.store.stock.StockData;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.XS_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.XL_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends PanacheEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(
            name = "stockData_id",
            referencedColumnName = "id",
            nullable = false
    )
    public StockData stockData;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<OfferOnProduct> offers;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<PurchaseOrderDetail> purchaseOrderDetails;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<ShoppingCartEntry> shoppingCartEntries;

    @ManyToOne
    @JoinColumn(name = "productSubcategory_id", nullable = false)
    @JsonManagedReference
    public ProductSubcategory subcategory;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_keywords",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "keyword_id")}
    )
    public List<Keyword> keywords;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_pictures",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    public List<Picture> pictures;

    @Column(length = L_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String name;

    @Column(length = XL_TEXT_COLUMN_LENGTH)
    public String description;

    @Column(length = XS_TEXT_COLUMN_LENGTH)
    public String variantCode;

    @Column(length = M_TEXT_COLUMN_LENGTH)
    public String color;

    @Column(nullable = false)
    public Double price;
}
