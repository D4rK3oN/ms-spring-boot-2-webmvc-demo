package com.github.d4rk3on.demo.client.jsonplaceholder;

import com.github.d4rk3on.demo.client.jsonplaceholder.mapper.PhotoAlbumsMapper;
import com.github.d4rk3on.demo.client.jsonplaceholder.properties.JsonPlaceholderProperties;
import com.github.d4rk3on.demo.client.jsonplaceholder.response.FindAllPhotoAlbumsResponse;
import com.github.d4rk3on.demo.domain.Photo;
import com.github.d4rk3on.support.spring.boot.webmvc.logger.annotations.Loggable;
import com.github.d4rk3on.support.spring.boot.webmvc.webclient.utils.WebClientUtils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JsonPlaceholderClient {

  private static final String ERR_MSG = "[Json Placeholder] [%s] [%s]";

  private final JsonPlaceholderProperties properties;

  private final WebClient webclient;

  @Loggable(isDebug = true)
  public Mono<Map<Long, List<Photo>>> findAllPhotoAlbums() {

    val uri = properties.getUrl().concat(properties.getPhotoAlbumsEndpoint());

    return webclient
        .get()
        .uri(uri)
        .retrieve()
        .onStatus(
            HttpStatus::isError,
            clientResponse ->
                WebClientUtils.generateError(
                    String.format(ERR_MSG, HttpMethod.GET, uri), clientResponse))
        .bodyToMono(
            new ParameterizedTypeReference<
                List<FindAllPhotoAlbumsResponse.JsonPlaceHolderPhoto>>() {})
        .flatMap(PhotoAlbumsMapper::toPhotoAlbumMap);
  }
}
