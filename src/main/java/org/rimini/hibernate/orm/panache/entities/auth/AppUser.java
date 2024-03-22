package org.rimini.hibernate.orm.panache.entities.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.rimini.hibernate.orm.panache.entities.store.customer.Customer;

import java.time.LocalDateTime;

import static org.rimini.constant.GlobalStaticData.AccountStatus.ACTIVE;
import static org.rimini.constant.GlobalStaticData.AppUserRole.BUSINESS_ADMIN;
import static org.rimini.constant.GlobalStaticData.AppUserRole.SYSTEM_ADMIN;
import static org.rimini.constant.GlobalStaticData.S_TEXT_COLUMN_LENGTH;
import static org.rimini.constant.GlobalStaticData.L_TEXT_COLUMN_LENGTH;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UserDefinition
public class AppUser extends PanacheEntity {

    private static final AppUserCommand CREATE_EXAMPLE_BUSINESS_ADMIN_COMMAND
            = AppUserCommand.builder()
            .username("ba")
            .email("ba@example.com")
            .password("p4ssw0rd")
            .build();

    private static final AppUserCommand CREATE_EXAMPLE_SYSTEM_ADMIN_COMMAND
            = AppUserCommand.builder()
            .username("sa")
            .email("sa@example.com")
            .password("p4ssw0rd")
            .build();

    @OneToOne(mappedBy = "appUser")
    @JsonBackReference
    public Customer customer;

    @Username
    public String username;

    @Column(length = L_TEXT_COLUMN_LENGTH, unique = true, nullable = false)
    public String email;

    @Password
    public String password;

    @Roles
    public String role;

    @Column(nullable = false)
    public LocalDateTime timestampCreated;

    @Column(nullable = false)
    public Boolean emailVerified;

    @Column(length = S_TEXT_COLUMN_LENGTH)
    public String accountStatus;

    public static AppUser findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public static void addBusinessAdmin(
            AppUserCommand createBusinessAdminCommand) {
        AppUser businessAdmin = AppUser.builder()
                .customer(null)
                .username(createBusinessAdminCommand.getUsername())
                .email(createBusinessAdminCommand.getEmail())
                .password(BcryptUtil.bcryptHash(createBusinessAdminCommand
                        .getPassword()))
                .role(BUSINESS_ADMIN.name())
                .timestampCreated(LocalDateTime.now())
                .emailVerified(false)
                .accountStatus(ACTIVE.name())
                .build();

        businessAdmin.persist();
    }

    public static void addSystemAdmin(
            AppUserCommand createSystemAdminCommand) {
        AppUser systemAdmin = AppUser.builder()
                .customer(null)
                .username(createSystemAdminCommand.getUsername())
                .email(createSystemAdminCommand.getEmail())
                .password(BcryptUtil.bcryptHash(createSystemAdminCommand
                        .getPassword()))
                .role(SYSTEM_ADMIN.name())
                .timestampCreated(LocalDateTime.now())
                .emailVerified(false)
                .accountStatus(ACTIVE.name())
                .build();

        systemAdmin.persist();
    }

    public static void addExampleAdmins() {
        addBusinessAdmin(CREATE_EXAMPLE_BUSINESS_ADMIN_COMMAND);
        addSystemAdmin(CREATE_EXAMPLE_SYSTEM_ADMIN_COMMAND);
    }
}
