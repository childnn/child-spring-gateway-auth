package org.child.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
public class JwtUtil {

    private static final String SALT = "颜值";

    public static String getToken(Map<String, ?> payload) {
        return JWT.create()
                .withPayload(payload)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(Algorithm.HMAC256(SALT));
    }

    public static DecodedJWT parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }

}
