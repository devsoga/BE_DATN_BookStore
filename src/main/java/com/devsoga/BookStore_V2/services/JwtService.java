package com.devsoga.BookStore_V2.services;


import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.devsoga.BookStore_V2.enties.AccountEntity;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String secretKey;


    public String generateAccessToken(AccountEntity account) {
        
        // phan header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // phan payload
        Date issueTime = new Date(); // time hien tai
        Date expireTime = Date.from(issueTime.toInstant().plus(30,ChronoUnit.MINUTES)); // thoi gian het han 30 phut sau        
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
            .subject(account.getUsername())
            .issueTime(issueTime)
            .expirationTime(expireTime)
            .build();
        Payload payload = new Payload(claims.toJSONObject());

        // phan signature

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (Exception e) {
            throw new RuntimeException("Error while signing the token");
        }

        return jwsObject.serialize();
    }

    public String generateRefreshToken(AccountEntity account) {
       // phan header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // phan payload
        Date issueTime = new Date(); // time hien tai
        Date expireTime = Date.from(issueTime.toInstant().plus(30,ChronoUnit.DAYS)); // thoi gian het han 30 phut sau        
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
            .subject(account.getUsername())
            .issueTime(issueTime)
            .expirationTime(expireTime)
            .build();
        Payload payload = new Payload(claims.toJSONObject());

        // phan signature

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (Exception e) {
            throw new RuntimeException("Error while signing the token");
        }

        return jwsObject.serialize();
    }


     public boolean verifyToken(String token) throws ParseException, JOSEException {
        try {
            // Parse token
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Kiểm tra thời hạn token
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime.before(new Date())) {
                System.out.println("Token expired");
                return false;
            }

            // Xác minh chữ ký
            boolean isValid = signedJWT.verify(new MACVerifier(secretKey));
            if (!isValid) {
                System.out.println("Invalid signature");
            }
            return isValid;

        } catch (ParseException e) {
            System.out.println("Invalid token format: " + e.getMessage());
            return false;
        } catch (JOSEException e) {
            System.out.println("Token verification error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return false;
        }

        
    }

}
