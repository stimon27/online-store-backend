package org.rimini.hibernate.orm.panache.entities.store.customer;

import lombok.Builder;
import lombok.Getter;
import org.rimini.hibernate.orm.panache.entities.auth.AppUserCommand;

@Getter
@Builder
public class CustomerCommand {
    private AppUserCommand appUserCommand;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String country;
    private String firstName;
    private String lastName;
    private String title;
    private String phone;
}
