package com.sasibhumaraju.job_portal.config;

import com.sasibhumaraju.job_portal.user.AppUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${SecretKey}")
    private String SECRET_KEY;
    private final AppUserService appUserService;


    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String buildToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails);
    }

    public String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] secretKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretKey);
    }
}
