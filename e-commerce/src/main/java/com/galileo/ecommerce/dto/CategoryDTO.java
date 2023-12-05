package com.galileo.ecommerce.dto;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class CategoryDTO {

  private Long id;

  private String name;

  private String description;
}
