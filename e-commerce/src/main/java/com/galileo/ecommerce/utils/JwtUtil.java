package com.galileo.ecommerce.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
  public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

  public String extractUsername(String token){
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String generateToken(String userName){
   Map<String,Object> claims = new HashMap<>();
    return createToken(claims,userName);

    /*
    Map<String,Object> claims = new HashMap<>();
    String token = createToken(claims,userName);
    System.out.println("Generated Token: " + token); // Add this line for logging
    return token;
    */
  }

  private String createToken(Map<String, Object> claims, String userName){
    return  Jwts.builder()
      .setClaims(claims)
      .setSubject(userName)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
      .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  private Key getSignKey()  {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}

/*

Bu Java sınıfı, JSON Web Token (JWT) oluşturma, doğrulama ve çözme işlemlerini gerçekleştirmek üzere tasarlanmış
 bir yardımcı sınıf olan JwtUtil'i temsil eder. JWT'ler,
  özellikle kimlik doğrulama (authentication) amacıyla kullanılan bir token tabanlı kimlik doğrulama
  mekanizmasıdır.

İşte bu sınıfın temel işlevselliği:

Sınıf Bilgileri:

JwtUtil adında bir yardımcı sınıftır.
@Component notasyonu ile işaretlenmiştir, bu da Spring tarafından yönetilen bir bileşen olduğunu belirtir.
JWT Oluşturma:

generateToken metodu, belirli bir kullanıcı adı için bir JWT oluşturur.
createToken metodu, JWT'nin temel yapılandırmasını oluşturur. JWT'nin konusu (subject),
iddia tarihi (issuedAt), ve süresi (expiration) gibi bilgiler bu metod içinde belirlenir.
JWT, Secret anahtarı kullanılarak imzalanır (signWith(getSignKey(), SignatureAlgorithm.HS256)).
JWT Çözme:

extractUsername metodu, JWT'den kullanıcı adını çözer.
extractExpiration metodu, JWT'den geçerlilik süresini çözer.
extractClaim metodu, JWT'den belirli bir talebi (claim) çözer.
JWT Doğrulama:

validateToken metodu, bir JWT'nin geçerli olup olmadığını kontrol eder. Kullanıcının adı doğrulandıktan
 ve token süresi geçmemişse true döner.
Diğer Yardımcı Metotlar:

extractAllClaims metodu, JWT'den tüm talepleri çözer.
isTokenExpired metodu, JWT'nin süresinin geçip geçmediğini kontrol eder.
getSignKey metodu, kullanılan gizli anahtarı (secret key) elde eder.
Bu sınıf, genellikle Spring Security ile birlikte kullanılarak kullanıcı kimlik doğrulamasında
ve yetkilendirme süreçlerinde JWT tabanlı bir kimlik doğrulama mekanizması sağlar.
 generateToken metodunu kullanarak JWT oluşturulabilir ve daha sonra bu
 JWT'yi kullanarak kullanıcıları doğrulayabilirsiniz.



 */
