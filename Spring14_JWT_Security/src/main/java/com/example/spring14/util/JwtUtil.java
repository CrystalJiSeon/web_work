package com.example.spring14.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())//토큰 발급시 서명했던 키값도 일치하는지 확인한다
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>(extraClaims);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)//추가 정보
                .setSubject(subject)//토큰에 담긴 주요 정보(주로 userName)
                .setIssuer("your-issuer") // 추가된 issuer(발급한 서비스명 ex.웹브라우저의 요청/앱의 요청) => 웹에서 받은걸 모바일에서 못쓰게 교차 검증
                .setIssuedAt(new Date(System.currentTimeMillis()))//발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//파기되는 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)//HS256 알고리즘으로 서명
                .compact();
    }

    //token 검증하는 메소드
    public boolean validateToken(String token) {
    	//토큰에 담긴 추가 정보 얻어내기(role, issuer 등등)
    	Claims claims = extractAllClaims(token);
    	//토큰 유효 기간이 남아 있는지와 issuer 정보도 일치하는지 확인해서
    	boolean isValid=!isTokenExpired(token)&&"your-issuer".equals(claims.getIssuer());
    	//유효성 여부 리턴
    	return isValid;
    }
}
