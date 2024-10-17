package com.github.d4rk3on.demo.client.jsonplaceholder.mapper;

import com.github.d4rk3on.demo.client.jsonplaceholder.response.FindAllPhotoAlbumsResponse;
import com.github.d4rk3on.demo.domain.Photo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class PhotoAlbumsMapper {

  public static Mono<Map<Long, List<Photo>>> toPhotoAlbumMap(
      List<FindAllPhotoAlbumsResponse.JsonPlaceHolderPhoto> response) {

    if (CollectionUtils.isEmpty(response)) return Mono.empty();

    return Flux.fromIterable(response)
        .filter(Objects::nonNull)
        .groupBy(FindAllPhotoAlbumsResponse.JsonPlaceHolderPhoto::getAlbumId)
        .flatMap(group -> group.collectList().map(photos -> Map.entry(group.key(), photos)))
        .collectMap(Map.Entry::getKey, entry -> toPhotos(entry.getValue()));
  }

  private static List<Photo> toPhotos(
      List<FindAllPhotoAlbumsResponse.JsonPlaceHolderPhoto> photos) {

    return CollectionUtils.isEmpty(photos)
        ? Collections.emptyList()
        : photos.stream()
            .map(
                photo ->
                    Photo.builder()
                        .id(photo.getPhotoId())
                        .title(photo.getTitle())
                        .url(photo.getUrl())
                        .thumbnailUrl(photo.getThumbnailUrl())
                        .build())
            .toList();
  }
}
