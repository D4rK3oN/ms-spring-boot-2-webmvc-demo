package com.github.d4rk3on.demo.web.validator;

import com.github.d4rk3on.demo.web.request.AuthenticationSignInRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthenticationSignInValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {

    return AuthenticationSignInRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {}
}
