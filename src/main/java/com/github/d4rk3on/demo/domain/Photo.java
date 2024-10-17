package com.github.d4rk3on.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Photo {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
  private Long id;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "culpa odio esse rerum omnis laboriosam voluptate repudiandae")
  private String title;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "https://via.placeholder.com/600/d32776")
  private String url;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      example = "https://via.placeholder.com/150/d32776")
  private String thumbnailUrl;
}
