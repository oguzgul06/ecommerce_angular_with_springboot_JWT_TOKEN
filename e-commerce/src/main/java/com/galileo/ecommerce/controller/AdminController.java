package com.galileo.ecommerce.controller;

import com.galileo.ecommerce.dto.CategoryDTO;
import com.galileo.ecommerce.entities.Category;
import com.galileo.ecommerce.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

  @PostMapping("/category")
  public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {


    Category createdCategory = adminService.createCategory(categoryDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
  }
}
