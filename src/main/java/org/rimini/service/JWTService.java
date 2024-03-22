package org.rimini.service;

import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.util.KeyUtils;
import jakarta.enterprise.context.ApplicationScoped;
import org.rimini.hibernate.orm.panache.entities.auth.AppUser;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static org.rimini.constant.GlobalStaticData.AppUserRole.CUSTOMER;
import static org.rimini.constant.GlobalStaticData.AppUserRole.BUSINESS_ADMIN;
import static org.rimini.constant.GlobalStaticData.AppUserRole.SYSTEM_ADMIN;
import static org.rimini.constant.GlobalStaticData.JWT_ISSUER;

@ApplicationScoped
public final class JWTService {
    private static final int KEY_SIZE = 4096;

    public String getJWT(String email, String role) throws JWTServiceException {
        return switch (role) {
            case "CUSTOMER" -> getCustomerJWT(email);
            case "BUSINESS_ADMIN" -> getBusinessAdminJWT(email);
            case "SYSTEM_ADMIN" -> getSystemAdminJWT(email);
            default -> throw new JWTServiceException(
                    "Role " + role + " not supported."
            );
        };
    } // getJWT(

    private String generateToken(Long userId, String role)
            throws NoSuchAlgorithmException {
        KeyPair keyPair = KeyUtils
                .generateKeyPair(
                        KEY_SIZE,
                        SignatureAlgorithm.RS256
                );
        return Jwt
                .claims(Collections.singletonMap("userId", userId))
                .issuer(JWT_ISSUER)
                .groups(role)
                .subject(userId.toString())
                .innerSign(keyPair.getPrivate())
                .encrypt(keyPair.getPublic());
    }

    private String getCustomerJWT(String email) throws JWTServiceException {
        AppUser appUser = AppUser.findByEmail(email);
        if (appUser == null) {
            throw new JWTServiceException(
                    "User of email " + email + " not found."
            );
        }
        if (appUser.customer == null) {
            throw new JWTServiceException(
                    "User of email " + email
                            + " not mapped to a Customer account."
            );
        }
        try {
            return generateToken(appUser.id, CUSTOMER.name());
        } catch (NoSuchAlgorithmException e) {
            throw new JWTServiceException(
                    "RSA256 algorithm not available in the environment."
            );
        }
    }

    private String getBusinessAdminJWT(String email)
            throws JWTServiceException {
        return getAdminJWTHelper(email, BUSINESS_ADMIN.name());
    }

    private String getSystemAdminJWT(String email)
            throws JWTServiceException {
        return getAdminJWTHelper(email, SYSTEM_ADMIN.name());
    }

    private String getAdminJWTHelper(String email, String adminRole)
            throws JWTServiceException {
        AppUser appUser = AppUser.findByEmail(email);
        if (appUser == null) {
            throw new JWTServiceException(
                    "User of email " + email + " not found."
            );
        }
        if (!appUser.role.equals(adminRole)) {
            throw new JWTServiceException(
                    "User of email " + email + " does not have the "
                            + adminRole + " role."
            );
        }
        try {
            return generateToken(appUser.id, adminRole);
        } catch (NoSuchAlgorithmException e) {
            throw new JWTServiceException(
                    "RSA256 algorithm not available in the environment."
            );
        }
    }

    public static class JWTServiceException extends Exception {
        public JWTServiceException(String message) {
            super(message);
        }
    }
}
