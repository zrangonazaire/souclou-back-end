package com.souclou.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import com.souclou.entities.Departement;
import com.souclou.repositories.DepartementRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartementControllerTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  DepartementRepository departementRepository;

  static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mySQLContainer::getUsername);
    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  }

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port + "/souclou/api/v1";
    departementRepository.deleteAll();
  }

  @BeforeAll
  static void beforeAll() {
    mySQLContainer.start();
  }

  @AfterAll
  static void afterAll() {
    mySQLContainer.stop();
  }

  @Test
  void testDeleteDepartement() {}

  @Test
  void testFindAllDepartement() {
    Departement dep1 = new Departement();
    dep1.setCreationDate(Instant.now());
    dep1.setLastModifiedDate(Instant.now());
    dep1.setNomDepartement("dep 1");
    dep1.setDescDepartement("Departement 1");
    departementRepository.save(dep1);

    Departement dep2 = new Departement();
    dep2.setCreationDate(Instant.now());
    dep2.setLastModifiedDate(Instant.now());
    dep2.setNomDepartement("dep 1");
    dep2.setDescDepartement("Departement 1");
    departementRepository.save(dep2);
    List<Departement> departements = List.of(dep1, dep2);
    departementRepository.saveAll(departements);
    given()
      .contentType(ContentType.JSON)
      .when()
      .get("/departement/findAllDepartement")
      .then()
      .statusCode(200)
      .body(".", hasSize(2));
  }

  @Test
  void testFindByIdDepartement() {}

  @Test
  void testSaveOrUpdateDepartement() {}
}
