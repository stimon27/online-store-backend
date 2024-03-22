package org.rimini.hibernate.orm.panache.entities.store.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.elytron.security.common.BcryptUtil;
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
import org.rimini.hibernate.orm.panache.entities.auth.AppUserCommand;
import org.rimini.hibernate.orm.panache.entities.store.address.Address;
import org.rimini.hibernate.orm.panache.entities.store.order.PurchaseOrderHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.rimini.constant.GlobalStaticData.AccountStatus.ACTIVE;
import static org.rimini.constant.GlobalStaticData.AppUserRole.CUSTOMER;
import static org.rimini.constant.GlobalStaticData.XS_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.S_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends PanacheEntity {

    private static final CustomerCommand CREATE_EXAMPLE_CUSTOMER_COMMAND
            = CustomerCommand.builder()
            .appUserCommand(AppUserCommand.builder()
                    .username("jkowalski")
                    .email("an.kowalski@example.com")
                    .password("p4ssw0rd")
                    .build())
            .addressLine1("Aleje Jerozolimskie")
            .addressLine2("1")
            .city("Warszawa")
            .zipCode("00-001")
            .country("Polska")
            .firstName("Jan")
            .lastName("Kowalski")
            .title("Pan")
            .phone("123456789")
            .build();

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

    public static void addCustomer(CustomerCommand createCustomerCommand) {
        String email = createCustomerCommand.getAppUserCommand().getEmail();

        Address defaultAddress = Address.builder()
                .addressLine1(createCustomerCommand.getAddressLine1())
                .addressLine2(createCustomerCommand.getAddressLine2())
                .city(createCustomerCommand.getCity())
                .zipCode(createCustomerCommand.getZipCode())
                .country(createCustomerCommand.getCountry())
                .shippedOrders(new ArrayList<>())
                .build();

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .shoppingCartEntries(new ArrayList<>())
                .build();

        AppUser appUser = AppUser.builder()
                .username(createCustomerCommand
                        .getAppUserCommand().getUsername())
                .email(email)
                .password(BcryptUtil.bcryptHash(createCustomerCommand
                        .getAppUserCommand().getPassword()
                ))
                .role(CUSTOMER.name())
                .timestampCreated(LocalDateTime.now())
                .emailVerified(false)
                .accountStatus(ACTIVE.name())
                .build();

        Customer customer = Customer.builder()
                .defaultAddress(defaultAddress)
                .shoppingCart(shoppingCart)
                .appUser(appUser)
                .purchaseOrderHeaders(new ArrayList<>())
                .firstName(createCustomerCommand.getFirstName())
                .lastName(createCustomerCommand.getLastName())
                .title(createCustomerCommand.getTitle())
                .email(email)
                .phone(createCustomerCommand.getPhone())
                .build();

        defaultAddress.owner = customer;
        shoppingCart.customer = customer;
        appUser.customer = customer;

        customer.persist();
    }

    public static void addExampleCustomer() {
        addCustomer(CREATE_EXAMPLE_CUSTOMER_COMMAND);
    }
}
