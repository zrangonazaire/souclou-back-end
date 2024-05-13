package com.souclou;

import com.souclou.entities.Role;
import com.souclou.repositories.RoleRepository;
import java.time.Instant;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SouclouApplication {

  public static void main(String[] args) {
    SpringApplication.run(SouclouApplication.class, args);
  }

  // @Bean
  // public CommandLineRunner initData(RoleRepository roleRepository) {
  //   return args -> {
  //     Long nmbrH = roleRepository.count();
  //     if (nmbrH <= 0) {
  //       Role roleDto = new Role();
  //       Role roleDto1 = new Role();
  //       Role roleDto2 = new Role();
  //       roleDto.setId(1L);
  //       roleDto.setCreationDate(Instant.now());

  //       roleDto.setIdCreateur(0L);
  //       roleDto.setLastModifiedDate(Instant.now());
  //       roleDto.setRoleDescription("Role professeur");
  //       roleDto.setRoleName("PROFESSEUR");

  //       roleDto1.setId(2L);
  //       roleDto1.setCreationDate(Instant.now());

  //       roleDto1.setIdCreateur(0L);
  //       roleDto1.setLastModifiedDate(Instant.now());
  //       roleDto1.setRoleDescription("Role Eleve");
  //       roleDto1.setRoleName("ELEVE");

  //       roleDto1.setId(3L);
  //       roleDto2.setCreationDate(Instant.now());

  //       roleDto2.setIdCreateur(0L);
  //       roleDto2.setLastModifiedDate(Instant.now());
  //       roleDto2.setRoleDescription("Role Administrateur");
  //       roleDto2.setRoleName("ADMINISTRATEUR");
  //       roleRepository.saveAll(Arrays.asList(roleDto, roleDto1, roleDto2));
  //     }
  //   };
  // }
}
