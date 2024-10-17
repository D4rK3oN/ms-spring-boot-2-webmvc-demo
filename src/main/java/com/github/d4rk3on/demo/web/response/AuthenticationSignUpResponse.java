package com.github.d4rk3on.demo.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.d4rk3on.support.spring.boot.webmvc.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationSignUpResponse {

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

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Bearer")
  private boolean enabled;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-05-17T12:26:38.907+0000")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date created;
}
