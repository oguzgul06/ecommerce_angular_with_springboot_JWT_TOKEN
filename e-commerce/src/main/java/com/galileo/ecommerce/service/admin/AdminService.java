package com.galileo.ecommerce.service.admin;

import com.galileo.ecommerce.dto.CategoryDTO;
import com.galileo.ecommerce.entities.Category;


public interface AdminService {
  Category createCategory(CategoryDTO categoryDTO);
}
