package com.github.d4rk3on.demo.web;

import com.github.d4rk3on.demo.service.AuthenticationService;
import com.github.d4rk3on.demo.web.request.AuthenticationSignInRequest;
import com.github.d4rk3on.demo.web.request.AuthenticationSignUpRequest;
import com.github.d4rk3on.demo.web.response.AuthenticationSignInResponse;
import com.github.d4rk3on.demo.web.response.AuthenticationSignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/public/authentication")
@RequiredArgsConstructor
@RestController
@Tag(name = "Authentication")
@Validated
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @ApiResponse(responseCode = "201")
  @Operation(
      summary = "Sign up : Create new User",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              required = true,
              content = {
                @Content(schema = @Schema(implementation = AuthenticationSignUpRequest.class))
              }))
  @PostMapping(value = "/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationSignUpResponse createUser(
      @RequestBody @Valid AuthenticationSignUpRequest request) {

    return authenticationService.createUser(request).block();
  }

  @ApiResponse(responseCode = "200")
  @Operation(
      summary = "Sign in : Validate credentials and build a Bearer Token",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              required = true,
              content = {
                @Content(schema = @Schema(implementation = AuthenticationSignInRequest.class))
              }))
  @PostMapping(value = "/sign-in")
  public AuthenticationSignInResponse signInUser(
      @RequestBody @Valid AuthenticationSignInRequest request) {

    return authenticationService.signInUser(request).block();
  }
}
