package com.galileo.ecommerce.repository;

import com.galileo.ecommerce.entities.User;
import com.galileo.ecommerce.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


  User findFirstByEmail(String email);

  User findByUserRole(UserRole userRole);
}


/*

Bu Java sınıfı, Spring Data JPA kullanarak bir veritabanı tablosuyla etkileşimde bulunmak üzere tasarlanmış bir
JPA (Java Persistence API) repository'yi temsil eder. Bu repository, User sınıfının veritabanı işlemlerini
gerçekleştirmek için kullanılır.

İşte bu sınıfın özet bir açıklaması:

Sınıf Bilgileri:

UserRepository adında bir arayüz (interface) olarak tanımlanmıştır.
JpaRepository'den türetilmiştir. Bu, genel CRUD (Create, Read, Update, Delete) işlemlerini destekleyen
bir Spring Data JPA arayüzüdür.
User sınıfı için temel veritabanı işlemlerini sağlamak üzere tasarlanmıştır.
Bağımlılıklar:

User: UserRepository tarafından yönetilen varlık sınıfı.
İşlevselliği:

@Repository notasyonu, bu sınıfın bir Spring bileşeni olarak tanımlanmasını sağlar. Spring,
 bu repository'yi otomatik olarak bulup yönetebilir.
JpaRepository sınıfından türetilmiş olduğu için, UserRepository sınıfı
CRUD işlemlerini (kaydetme, okuma, güncelleme, silme) otomatik olarak sağlar.
Ayrıca, özel bir sorgu metodu (findFirstByEmail) tanımlanmıştır. Bu metot,
User sınıfında bulunan bir kullanıcıyı e-posta adresine göre bulmak için kullanılır. Bu tür özel sorgular,
Spring Data JPA tarafından otomatik olarak uygulanan sorgu oluşturma kurallarına uyar.
Bu repository, genellikle User sınıfı ile ilişkili veritabanı işlemlerini gerçekleştirmek üzere kullanılır.
Örneğin, kullanıcı ekleme, kullanıcı güncelleme, kullanıcı silme, kullanıcıları listeleme
gibi temel CRUD işlemleri bu repository üzerinden gerçekleştirilebilir.

 */
