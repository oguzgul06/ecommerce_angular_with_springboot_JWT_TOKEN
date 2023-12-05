package com.galileo.ecommerce.controller;

import com.galileo.ecommerce.dto.SignupDTO;
import com.galileo.ecommerce.dto.UserDTO;
import com.galileo.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {

  @Autowired
  private UserService userService;


  @PostMapping("/sign-up")
  public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Frame-Options", "DENY");


    if (userService.hasUserWithEmail(signupDTO.getEmail())) {

      // return new ResponseEntity<>("User already exist", headers,HttpStatus.NOT_ACCEPTABLE);
      return new ResponseEntity<>("User already exist", headers, HttpStatus.OK);
    }

    UserDTO createdUser = userService.createUSer(signupDTO);
    if (createdUser == null) {
      return new ResponseEntity<>("User not created. Come again later!", headers, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

}
