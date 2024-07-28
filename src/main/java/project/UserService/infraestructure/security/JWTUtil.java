package project.UserService.infraestructure.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTUtil {
    public static Key generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
