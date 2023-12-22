package org.microservices.Authenticationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {


    public static final String SECRET = "39967fa6238249dfc017d7f49f1e77a15e7b42c6c75812490d25bf56b0d5e692";


    public void validateToken(final String token){
        Jws<Claims> claimaJws = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }
    public String genarateToken(String name){
        Map<String,Object> claims = new HashMap<String,Object>();
        return createToken(claims, name);
    }

    private String createToken(Map<String, Object> claims, String name) {
       return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] signature= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(signature);
    }
}
