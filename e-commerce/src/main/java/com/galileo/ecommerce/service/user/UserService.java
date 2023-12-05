package com.galileo.ecommerce.service.user;

import com.galileo.ecommerce.dto.SignupDTO;
import com.galileo.ecommerce.dto.UserDTO;

public interface UserService {
  UserDTO createUSer(SignupDTO signupDTO);

  boolean hasUserWithEmail(String email);
}
