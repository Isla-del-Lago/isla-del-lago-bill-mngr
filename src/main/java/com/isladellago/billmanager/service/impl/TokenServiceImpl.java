package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.service.TokenService;
import com.isladellago.billmanager.util.CustomJwtClaims;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TokenServiceImpl implements TokenService {

    private String jwtSignatureSecret;

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSignatureSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSignatureSecret)
                .parseClaimsJws(token)
                .getBody()
                .get(CustomJwtClaims.EMAIL_CLAIM, String.class);
    }

    @Value("${jwt.signature.secret}")
    public void setJwtSignatureSecret(String jwtSignatureSecret) {
        this.jwtSignatureSecret = jwtSignatureSecret;
    }
}
