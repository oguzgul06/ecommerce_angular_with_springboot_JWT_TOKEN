package com.galileo.ecommerce.service.admin;

import com.galileo.ecommerce.dto.CategoryDTO;
import com.galileo.ecommerce.entities.Category;
import com.galileo.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category createCategory(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setName(categoryDTO.getName());
    category.setDescription(categoryDTO.getDescription());
    return categoryRepository.save(category);
  }
}
