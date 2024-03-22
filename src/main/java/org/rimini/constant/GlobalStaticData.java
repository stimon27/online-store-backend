package org.rimini.constant;

public final class GlobalStaticData {
    private GlobalStaticData() { }
    public static final int XS_TEXT_COLUMN_LENGTH = 6;
    public static final int S_TEXT_COLUMN_LENGTH = 20;
    public static final int M_TEXT_COLUMN_LENGTH = 50;
    public static final int L_TEXT_COLUMN_LENGTH = 100;
    public static final int XL_TEXT_COLUMN_LENGTH = 500;
    public static final String JWT_ISSUER = "https://rimini.org/issuer";

    public enum AppUserRole {
        CUSTOMER,
        BUSINESS_ADMIN,
        SYSTEM_ADMIN
    }

    public enum AccountStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        DELETED
    }
}
