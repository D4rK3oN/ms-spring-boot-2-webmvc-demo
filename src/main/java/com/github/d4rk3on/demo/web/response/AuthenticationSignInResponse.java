package com.github.d4rk3on.demo.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationSignInResponse {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "999fdd63-6e61-46c8-b877-ffb244cb395c")
  @ToString.Exclude
  private String uuid;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Deadpool")
  private String username;

  @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Wade Wilson")
  private String name;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "deadpool-winner@mail.com")
  private String email;

  @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "691196215")
  private String phone;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      example =
          "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZWFkcG9vbCIsImlhdCI6MTcyOTI2MDAzNSwiZXhwIjoxNzI5MjYwMzM1fQ.O-J9MVS3GXXHecJG0Ke-Rp7nrFVP-PNwRpHEuYNfBS8")
  @ToString.Exclude
  private String token;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Bearer")
  @ToString.Exclude
  private String type;

  @ArraySchema(
      schema = @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "ROLE_USER"),
      uniqueItems = true)
  private List<String> roles;
}
