package com.galileo.ecommerce.filters;


import com.galileo.ecommerce.service.jwt.UserDetailsServiceImpl;
import com.galileo.ecommerce.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      username = jwtUtil.extractUsername(token);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (jwtUtil.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}


/*
KOD AÇIKLAMASI:

Bu Java sınıfı, Spring Security ile birlikte kullanılan bir JWT (JSON Web Token) doğrulama filtresini temsil
eder. JWT, kullanıcı kimlik doğrulaması için kullanılan bir token tabanlı bir kimlik doğrulama mekanizmasıdır.

Aşağıda bu sınıfın özet bir açıklamasını bulabilirsiniz:

Sınıf Bilgileri:

Sınıf, JwtRequestFilter adında ve OncePerRequestFilter sınıfından türetilmiş bir sınıftır. Bu, her gelen HTTP
isteği için yalnızca bir kez çalışacak şekilde tasarlanmıştır.
Bağımlılıklar:

UserDetailsServiceImpl: Spring Security için bir kullanıcı detayları servisi. Kullanıcı bilgilerini sağlamak
ve işlemek için kullanılır.

JwtUtil: JWT oluşturma, çözme ve doğrulama işlemlerini gerçekleştiren bir yardımcı sınıf.
Filtreleme İşlemi (doFilterInternal Metodu):

Bu sınıfın en önemli metodu, doFilterInternal metodudur. Bu metot, her gelen HTTP isteği için çağrılır ve
JWT doğrulama sürecini gerçekleştirir.
İlk olarak, HTTP isteğinin "Authorization" başlığını kontrol eder ve bir JWT içerip içermediğini kontrol eder.
Eğer varsa, bu JWT'den kullanıcı adını çıkarır.
Kullanıcı adı varsa ve kullanıcı daha önce doğrulanmamışsa, UserDetailsServiceImpl kullanılarak kullanıcı
detayları alınır.
Alınan kullanıcı detayları ve JWT'nin doğruluğu kontrol edilir (jwtUtil.validateToken).
Eğer JWT doğrulandıysa, bir UsernamePasswordAuthenticationToken oluşturulur ve
Spring Security kontekstine eklenir. Bu işlem, kullanıcının kimlik doğrulama sürecini başlatır.
Son olarak, işlem devam eder ve diğer filtrelere geçilir (filterChain.doFilter(request, response)).
Bu filtre, gelen her HTTP isteğini kontrol eder, eğer uygun bir JWT bulunursa ve bu JWT geçerliyse,
kullanıcının kimlik doğrulama işlemini gerçekleştirir ve güvenlik bağlamını günceller. Bu sayede,
Spring Security'nin sunduğu güvenlik özellikleri, kullanıcı kimlik doğrulaması ve yetkilendirme işlemleri
JWT tabanlı olarak gerçekleştirilmiş olur.

 */
