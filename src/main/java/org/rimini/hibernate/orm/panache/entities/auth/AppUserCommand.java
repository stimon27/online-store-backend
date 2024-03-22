package org.rimini.hibernate.orm.panache.entities.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppUserCommand {
    private String username;
    private String email;
    private String password;
}
