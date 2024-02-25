package org.rimini.hibernate.orm.panache.entities.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.customer.Customer;

import java.time.LocalDateTime;

import static org.rimini.constant.GlobalStaticData.S_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.M_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.XL_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends PanacheEntity {
    @OneToOne(mappedBy = "appUser")
    @JsonBackReference
    public Customer customer;

    @Column(length = M_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String userName;

    @Column(length = L_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String email;

    @Column(length = XL_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    public LocalDateTime timestampCreated;

    @Column(nullable = false)
    public Boolean emailVerified;

    @Column(length = S_TEXT_COLUMN_LENGTH)
    public String accountStatus;
}
