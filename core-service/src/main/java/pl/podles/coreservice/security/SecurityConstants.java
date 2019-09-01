package pl.podles.coreservice.security;

public class SecurityConstants {
    public static final String SECRET = "PtakiLecaKluczem";
    public static final long EXPIRATION_TIME = 600000; //10 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PERMITTED_URL = {"/sign-up-customer", "/sign-up-admin"};
}
