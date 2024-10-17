package com.github.d4rk3on.demo.web;

import com.github.d4rk3on.demo.domain.Photo;
import com.github.d4rk3on.demo.service.PhotoAlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/private/photo-albums")
@RequiredArgsConstructor
@RestController
@Tag(name = "Photo Albums")
public class PhotoAlbumController {

  private final PhotoAlbumService photoAlbumService;

  @ApiResponse(responseCode = "200")
  @GetMapping
  @Operation(summary = "Find all Photo Albums")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @SecurityRequirement(name = "Bearer Authentication")
  public Map<Long, List<Photo>> findAll() {

    return photoAlbumService.findAll().block();
  }
}
