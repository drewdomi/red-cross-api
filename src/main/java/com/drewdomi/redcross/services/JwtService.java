package com.drewdomi.redcross.services;

import com.drewdomi.redcross.models.Rescuer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    private Key getSignInKey() {
        final var key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
            .parser()
            .verifyWith((SecretKey) getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String buildToken(
        Map<String, Object> extraClaims,
        Rescuer userDetails,
        long expiration) {

        Map<String, Object> claims = new HashMap<>(extraClaims);
        claims.put("id", userDetails.getId());
        claims.put("numMechanographic", userDetails.getNumMechanographic());
        claims.put("email", userDetails.getUsername());
        claims.put("access", userDetails.getAccessType().name());

        return Jwts
            .builder()
            .claims(claims)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey())
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final var username = this.extractAllClaims(token).get("email", String.class);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Rescuer userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken(
        Rescuer userDetails,
        Map<String, Object> extraClaims) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

}
