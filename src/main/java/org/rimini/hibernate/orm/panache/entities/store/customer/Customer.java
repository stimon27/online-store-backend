package org.rimini.hibernate.orm.panache.entities.store.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.auth.AppUser;
import org.rimini.hibernate.orm.panache.entities.store.address.Address;
import org.rimini.hibernate.orm.panache.entities.store.order.PurchaseOrderHeader;

import java.util.List;

import static org.rimini.constant.GlobalStaticData.XS_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.S_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends PanacheEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
            name = "customer_address",
            joinColumns =
                    { @JoinColumn(
                            name = "customer_id",
                            referencedColumnName = "id"
                    ) },
            inverseJoinColumns =
                    { @JoinColumn(
                            name = "address_id",
                            referencedColumnName = "id"
                    ) }
    )
    public Address defaultAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(
            name = "shoppingCart_id",
            referencedColumnName = "id",
            nullable = false
    )
    public ShoppingCart shoppingCart;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(
            name = "appUser_id",
            referencedColumnName = "id",
            nullable = false
    )
    public AppUser appUser;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<PurchaseOrderHeader> purchaseOrderHeaders;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String firstName;

    @Column(length = M_TEXT_COLUMN_LENGTH, nullable = false)
    public String lastName;

    @Column(length = XS_TEXT_COLUMN_LENGTH)
    public String title;

    @Column(length = L_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String email;

    @Column(length = S_TEXT_COLUMN_LENGTH, nullable = false)
    public String phone;
}
