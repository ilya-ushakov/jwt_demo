package com.jooq.proto.jwtangspr;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ilya on 4/25/17.
 */
public enum JWTTokenGenerator {

    INSTANCE;

    private Map<String, List<String>> userDb;

    private String secretKey;

    String generateToken(final String login){
        final String token = Jwts.builder().setSubject(login)
                .claim("roles", userDb.get(login)).setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS256, getSecretKey()).compact();

        return token;
    }

    void generateRandomSecretKey(){
        secretKey = UUID.randomUUID().toString();
    }

    public Map<String, List<String>> getUserDb() {
        if(userDb == null)
            userDb  = new ConcurrentHashMap<>();
        return userDb;
    }

    public synchronized String getSecretKey() {
        return secretKey;
    }

    private Date getExpirationDate(){
        final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

        Calendar date = Calendar.getInstance();
        long t= date.getTimeInMillis();
        Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));

        return afterAddingTenMins;
    }
}
