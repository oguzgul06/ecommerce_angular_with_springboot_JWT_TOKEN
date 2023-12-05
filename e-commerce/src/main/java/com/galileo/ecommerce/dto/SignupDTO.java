package com.galileo.ecommerce.dto;


import lombok.Data;

@Data
public class SignupDTO {

  private String name;

  private String email;

  private String password;
}


/*


Bu Java kodu, bir kullanıcının kaydolma işlemi için kullanılabilecek bir veri transfer
nesnesini (DTO - Data Transfer Object) temsil eder. İlgili sınıf,
SignupDTO adında bir sınıftır ve com.galileo.ecommerce.dto paketi altında bulunmaktadır.

İşlevsel olarak bu sınıf, kullanıcının kaydolma işlemi için gerekli olan temel bilgileri içerir.
Bu bilgiler şunlardır:

name: Kullanıcının adını temsil eden bir String.
email: Kullanıcının e-posta adresini temsil eden bir String.
password: Kullanıcının şifresini temsil eden bir String.
Bu sınıfın amacı, kullanıcıdan alınan bu temel bilgileri bir nesne içinde gruplayarak,
bu bilgileri kolayca taşıyabilmek ve işleyebilmektir. Ayrıca,
@Data annotasyonu Lombok kütüphanesinden gelmektedir ve bu annotasyon sayesinde sınıf içinde getter,
setter ve diğer yaygın metotları otomatik olarak oluşturur.
Bu, kodun daha kısa ve okunabilir olmasına yardımcı olur.

 */
