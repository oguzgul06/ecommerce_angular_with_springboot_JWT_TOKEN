package com.galileo.ecommerce.service.jwt;

import com.galileo.ecommerce.entities.User;
import com.galileo.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  @Autowired
  private UserRepository userRepo;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findFirstByEmail(username);
    if (user == null) throw new UsernameNotFoundException("Username not found", null);
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

  }
}


/*

Bu Java sınıfı, Spring Security tarafından kullanılan UserDetailsService arayüzünü uygulayan
bir servis sınıfını temsil eder. UserDetailsService, Spring Security'nin kullanıcı kimlik
doğrulamasını gerçekleştirmek için kullanılan bir arayüzdür.
Bu arayüzü uygulayan sınıflar, genellikle kullanıcı bilgilerini sağlamak üzere tasarlanmıştır.

İşte bu sınıfın özet bir açıklaması:

Sınıf Bilgileri:

UserDetailsServiceImpl adında bir servis sınıfıdır.
UserDetailsService arayüzünü implemente eder.
Bağımlılıklar:

UserRepository: Kullanıcı veritabanı işlemlerini gerçekleştirmek üzere tasarlanmış
bir Spring Data JPA repository'si.
İşlevselliği:

@Service notasyonu, bu sınıfın bir Spring servisi olarak tanımlanmasını sağlar. Spring,
bu servisi otomatik olarak bulup yönetebilir.
UserDetailsServiceImpl sınıfı, UserDetailsService arayüzünü uygular.
Bu arayüz, loadUserByUsername metoduyla tanımlı bir metod içerir.
loadUserByUsername metodu, kullanıcı adına (e-posta adresine) göre kullanıcı bilgilerini sağlar.
 Bu metod, UserRepository kullanarak veritabanından kullanıcıyı bulur.
Eğer kullanıcı bulunamazsa, UsernameNotFoundException fırlatılır.
Bu durum genellikle kullanıcı adının (e-posta adresinin) geçerli bir kullanıcıya ait
olup olmadığını kontrol etmek için kullanılır.
Kullanıcı bulunduğunda, org.springframework.security.core.userdetails.User
sınıfı kullanılarak bir UserDetails nesnesi oluşturulur.
Bu nesne, Spring Security tarafından kullanılan bir kullanıcı detayıdır ve
kimlik doğrulaması sürecinde kullanılır. ArrayList<> ile boş bir yetki listesi atanmıştır,
ancak gerçek uygulamada kullanıcı yetkileri eklenerek güvenlik özellikleri uygulanabilir.
Bu servis sınıfı, genellikle Spring Security konfigürasyonu içinde kullanılarak kullanıcı
kimlik doğrulaması için gerekli olan kullanıcı bilgilerini sağlar.




 */
