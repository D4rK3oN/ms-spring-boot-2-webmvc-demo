package com.github.d4rk3on.demo.service;

import com.github.d4rk3on.demo.web.request.AuthenticationSignInRequest;
import com.github.d4rk3on.demo.web.request.AuthenticationSignUpRequest;
import com.github.d4rk3on.demo.web.response.AuthenticationSignInResponse;
import com.github.d4rk3on.demo.web.response.AuthenticationSignUpResponse;
import com.github.d4rk3on.support.spring.boot.webmvc.common.exception.FunctionalException;
import com.github.d4rk3on.support.spring.boot.webmvc.common.model.UserDetailsImpl;
import com.github.d4rk3on.support.spring.boot.webmvc.security.entity.User;
import com.github.d4rk3on.support.spring.boot.webmvc.security.service.JwtService;
import com.github.d4rk3on.support.spring.boot.webmvc.security.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserService userService;

  @Override
  public Mono<AuthenticationSignUpResponse> createUser(AuthenticationSignUpRequest request) {

    return alreadyExists(request.getUsername(), request.getEmail())
        .flatMap(
            exists ->
                exists != null && exists
                    ? Mono.error(
                        new FunctionalException("User already exists", HttpStatus.CONFLICT))
                    : Mono.justOrEmpty(
                        userService.save(
                            User.builder()
                                .username(request.getUsername())
                                .password(request.getPassword())
                                .name(request.getName())
                                .email(request.getEmail())
                                .phone(request.getPhone())
                                .build())))
        .map(
            user ->
                AuthenticationSignUpResponse.builder()
                    .uuid(user.getId().toString())
                    .username(user.getUsername())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .enabled(user.isEnabled())
                    .created(user.getCreated())
                    .build());
  }

  private Mono<Boolean> alreadyExists(String username, String email) {

    return Flux.just(userService.findByUsername(username), userService.findByEmail(email))
        .filter(Optional::isPresent)
        .map(Optional::get)
        // .distinct()
        .collectList()
        .map(users -> !CollectionUtils.isEmpty(users))
        .defaultIfEmpty(false);
  }

  @Override
  public Mono<AuthenticationSignInResponse> signInUser(AuthenticationSignInRequest request) {

    return Mono.justOrEmpty(
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword())))
        .map(
            auth -> {
              SecurityContextHolder.getContext().setAuthentication(auth);

              val userDetails = (UserDetailsImpl) auth.getPrincipal();

              return AuthenticationSignInResponse.builder()
                  .uuid(userDetails.getUuid())
                  .username(userDetails.getUsername())
                  .name(userDetails.getName())
                  .email(userDetails.getEmail())
                  .phone(userDetails.getPhone())
                  .token(jwtService.buildToken(auth))
                  .type("Bearer")
                  .roles(
                      userDetails.getAuthorities().stream()
                          .map(GrantedAuthority::getAuthority)
                          .toList())
                  .build();
            });
  }
}
