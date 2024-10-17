package com.github.d4rk3on.demo.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class AuthenticationSignInRequest {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Deadpool")
  @NotBlank(message = "'username' is required")
  @Size(min = 6, message = "'username' must be at least 6 characters")
  @Size(max = 20, message = "'username' can be a maximum of 20 characters")
  private String username;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "byebyebye")
  @NotBlank(message = "'password' is required")
  @Size(min = 4, message = "'password' must be at least 4 characters")
  @Size(max = 20, message = "'password' can be up to 20 characters")
  @ToString.Exclude
  private String password;
}
