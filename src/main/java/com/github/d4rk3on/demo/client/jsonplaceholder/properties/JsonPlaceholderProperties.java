package com.github.d4rk3on.demo.client.jsonplaceholder.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "http.clients.json-placeholder")
@Data
public class JsonPlaceholderProperties {

  private String url;

  private String photoAlbumsEndpoint;
}
