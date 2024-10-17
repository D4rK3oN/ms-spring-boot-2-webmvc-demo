package com.github.d4rk3on.demo.service;

import com.github.d4rk3on.demo.web.request.AuthenticationSignInRequest;
import com.github.d4rk3on.demo.web.request.AuthenticationSignUpRequest;
import com.github.d4rk3on.demo.web.response.AuthenticationSignInResponse;
import com.github.d4rk3on.demo.web.response.AuthenticationSignUpResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationService {

  Mono<AuthenticationSignUpResponse> createUser(AuthenticationSignUpRequest request);

  Mono<AuthenticationSignInResponse> signInUser(AuthenticationSignInRequest request);
}
