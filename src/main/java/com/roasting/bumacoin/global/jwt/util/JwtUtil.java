package com.roasting.bumacoin.global.jwt.util;

import com.roasting.bumacoin.global.jwt.config.JwtProperties;
import com.roasting.bumacoin.global.jwt.exception.ExpiredJwtException;
import com.roasting.bumacoin.global.jwt.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearer) {
        if (bearer != null && bearer.startsWith(jwtProperties.getPrefix())) {
            return bearer.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    public Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }
}
