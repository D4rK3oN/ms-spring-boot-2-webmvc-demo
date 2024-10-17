package com.github.d4rk3on.demo.service;

import com.github.d4rk3on.demo.domain.Photo;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

public interface PhotoAlbumService {

  Mono<Map<Long, List<Photo>>> findAll();
}
