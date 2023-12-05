package com.galileo.ecommerce.configuration;

import com.galileo.ecommerce.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration  {

@Autowired
private JwtRequestFilter authFilter;

/*
  @Bean
  private SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http.csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers("/authenticate", "/sign-up").permitAll()
      .and()
      .authorizeHttpRequests().requestMatchers("/api/**")
      .authenticated().and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }*/

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    security
      .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
      .csrf(AbstractHttpConfigurer::disable)
      .formLogin(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(x-> x.requestMatchers("/authenticate", "/sign-up").permitAll())
      .authorizeHttpRequests(x-> x.requestMatchers("/api/**"))
      .authorizeHttpRequests(x->x.anyRequest().authenticated())
      //.httpBasic(Customizer.withDefaults())
      .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    return security.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
     return  config.getAuthenticationManager();
  }

}
