package com.galileo.ecommerce.entities;


import com.galileo.ecommerce.dto.UserDTO;
import com.galileo.ecommerce.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String email;

  private String password;

  private UserRole userRole;

  private byte[] img;



}
