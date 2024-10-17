package com.github.d4rk3on.demo.service;

import com.github.d4rk3on.demo.client.jsonplaceholder.JsonPlaceholderClient;
import com.github.d4rk3on.demo.domain.Photo;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PhotoAlbumServiceImpl implements PhotoAlbumService {

  private final JsonPlaceholderClient jsonPlaceholderClient;

  @Override
  public Mono<Map<Long, List<Photo>>> findAll() {

    return jsonPlaceholderClient.findAllPhotoAlbums();
  }
}
