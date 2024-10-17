package com.github.d4rk3on.demo.client.jsonplaceholder.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FindAllPhotoAlbumsResponse {

  @AllArgsConstructor
  @Builder(toBuilder = true)
  @Data
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @NoArgsConstructor
  public static class JsonPlaceHolderPhoto {

    private Long albumId;

    @JsonProperty("id")
    private Long photoId;

    private String title;

    private String url;

    private String thumbnailUrl;
  }
}
