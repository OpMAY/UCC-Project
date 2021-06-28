package com.restapi.Restfull.API.Server.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

@Log4j2
@Service
public class SecurityService {
    @Value("${api.access_key}")
    private String access_key;

    public String encryptionJWT(Auth auth) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withExpiresAt(Time.LongTimeStamp())
                .withClaim("signature", encryptionSHA256(access_key))
                .withClaim("name", auth.getName())
                .withClaim("permission", "grant")
                .withIssuer("auth0")
                .sign(algorithm);
    }

    /**
     * SHA-256으로 해싱하는 메소드
     *
     * @param msg
     * @return bytes
     * @throws NoSuchAlgorithmException
     */
    public String encryptionSHA256(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    /**
     * 바이트를 헥스값으로 변환한다.
     *
     * @param bytes
     * @return
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public String createToken(Auth auth) {
        return encryptionJWT(auth);
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String name = jwt.getClaim("name").asString();
            String signature = jwt.getClaim("signature").asString();

            /** Signature & name Valid Checker*/
            if (!signature.equals(encryptionSHA256(access_key)) || !name.equals("test")) {
                return false;
            }

            /** Timer Limit Valid Checker*/
            if (!(jwt.getExpiresAt().getTime() <= Time.LongTimeStamp().getTime())) {
                return false;
            }

            return true;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (JWTDecodeException e) {
            throw new BusinessException(e);
        } catch (JWTVerificationException e) {
            throw new BusinessException(e);
        } catch (JWTCreationException e) {
            throw new BusinessException(e);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
