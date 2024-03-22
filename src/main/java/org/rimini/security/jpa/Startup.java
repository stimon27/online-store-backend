package org.rimini.security.jpa;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.rimini.hibernate.orm.panache.entities.auth.AppUser;
import org.rimini.hibernate.orm.panache.entities.store.customer.Customer;

@Singleton
public final class Startup {

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        Customer.deleteAll();
        AppUser.deleteAll();
        Customer.addExampleCustomer();
        AppUser.addExampleAdmins();
    }
}
