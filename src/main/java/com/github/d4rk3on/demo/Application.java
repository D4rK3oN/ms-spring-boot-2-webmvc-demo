package com.github.d4rk3on.demo;

import com.github.d4rk3on.support.spring.boot.webmvc.common.model.enums.RoleEnum;
import com.github.d4rk3on.support.spring.boot.webmvc.common.utils.DateUtils;
import com.github.d4rk3on.support.spring.boot.webmvc.security.entity.Role;
import com.github.d4rk3on.support.spring.boot.webmvc.security.entity.User;
import com.github.d4rk3on.support.spring.boot.webmvc.security.repository.RoleRepository;
import com.github.d4rk3on.support.spring.boot.webmvc.security.repository.UserRepository;
import java.util.List;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories("com.github.d4rk3on")
@EnableWebMvc
@EntityScan("com.github.d4rk3on")
@Generated
@RequiredArgsConstructor
@Slf4j
@SpringBootApplication(scanBasePackages = "com.github.d4rk3on")
public class Application {

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }

  @Bean
  InitializingBean init() {

    return () -> {
      val roleAdmin =
          roleRepository.save(
              Role.builder()
                  .name(RoleEnum.ROLE_ADMIN)
                  .enabled(true)
                  .created(DateUtils.now())
                  .build());

      val roleUser =
          roleRepository.save(
              Role.builder()
                  .name(RoleEnum.ROLE_USER)
                  .enabled(true)
                  .created(DateUtils.now())
                  .build());

      log.debug("'roles' created on init... : {}", roleRepository.findAll());

      userRepository.save(
          User.builder()
              .username("D4rK3oN")
              .password(passwordEncoder.encode("admin"))
              .name("Javier")
              .email("admin@mail.com")
              .phone("691186205")
              .enabled(true)
              .roles(List.of(roleAdmin, roleUser))
              .created(DateUtils.now())
              .build());

      userRepository.save(
          User.builder()
              .username("Deadpool")
              .password(passwordEncoder.encode("byebyebye"))
              .name("Wade Wilson")
              .email("deadpool-winner@mail.com")
              .phone("691196215")
              .enabled(true)
              .roles(List.of(roleUser))
              .created(DateUtils.now())
              .build());

      userRepository.save(
          User.builder()
              .username("Wolverine")
              .password(passwordEncoder.encode("fuckoff"))
              .name("James Logan Howlett")
              .email("wolverine@mail.com")
              .enabled(false)
              .created(DateUtils.now())
              .build());

      log.debug("'users' created on init...: {}", userRepository.findAll());
    };
  }
}
