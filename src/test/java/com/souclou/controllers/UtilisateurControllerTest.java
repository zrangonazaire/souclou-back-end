package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.souclou.dtos.RoleDto;
import com.souclou.dtos.UtilisateurDto;
import com.souclou.services.UtilisateurService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { UtilisateurController.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UtilisateurControllerTest {

  List<UtilisateurDto> utilisateurDtos;
  UtilisateurDto utilisateur = new UtilisateurDto();
  UtilisateurDto utilisateur2 = new UtilisateurDto();
  RoleDto role = new RoleDto();

  @Autowired
  private ObjectMapper om;

  @Autowired
  private WebApplicationContext applicationContext;

  @MockBean
  private UtilisateurService utilisateurService;

  // @MockBean
  // private RoleService roleService;

  @Autowired
  private MockMvc mockMvc;

  @Before
  void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    // doReturn(RoleDto.class).when(roleService).saveOrUpdate(role);
    // doReturn(UtilisateurDto.class)
    //   .when(utilisateurService)
    //   .saveOrUpdate(utilisateur);
  }

  @Test
  void testDeleteUtilisateur() throws Exception {
    long id = 1L;
    // when(utilisateurService).delete(id);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete(APP_ROOT + "/utilisateur/deleteUtilisateur/{id}", id)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testFindAllUtilisateur() throws Exception {
    role.setCreationDate(Instant.now());
    role.setLastModifiedDate(Instant.now());
    role.setRoleName("ELEVE");
    role.setRoleDescription("ROLE eleves");
    //user
    utilisateur.setId(1L);
    utilisateur.setCelUser("0708771317");
    utilisateur.setCreationDate(Instant.now());
    utilisateur.setGenreUser("M");
    utilisateur.setLastModifiedDate(Instant.now());
    utilisateur.setLoginUser("astairenazaire");
    utilisateur.setMailUser("astairenazaire@gmail.com");
    utilisateur.setMotDePasse("1234");
    utilisateur.setNomPUser("ZRANGO");
    utilisateur.setPrenomPrUser("NAZAIRE");
    utilisateur.setRoleName("ELEVE");
    utilisateur.setUrole(role);
    utilisateur.setTelMobileUser("0708771317");
    //user
    utilisateur2.setId(2L);
    utilisateur2.setCelUser("0708771318");
    utilisateur2.setCreationDate(Instant.now());
    utilisateur2.setGenreUser("F");
    utilisateur2.setLastModifiedDate(Instant.now());
    utilisateur2.setLoginUser("astaire");
    utilisateur2.setMailUser("astairenazaire1@gmail.com");
    utilisateur2.setMotDePasse("1234");
    utilisateur2.setNomPUser("ZRANGO");
    utilisateur2.setPrenomPrUser("NAZAIRE");
    utilisateur2.setRoleName("ELEVE");
    utilisateur2.setUrole(role);
    utilisateur2.setTelMobileUser("0708771318");
    utilisateurDtos = new ArrayList<>(Arrays.asList(utilisateur, utilisateur2));
    Mockito.when(utilisateurService.findAll()).thenReturn(utilisateurDtos);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/utilisateur/findAllUtilisateur")
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()").value(utilisateurDtos.size()))
      .andExpect(jsonPath("$.[0].id").value(1L))
      .andDo(print());
  }

  @Test
  void testFindAllUtilisateurAvecRole() throws Exception {
    String role = "ELEVE";
    List<UtilisateurDto> utilisateurDtos = new ArrayList<>(
      Arrays.asList(utilisateur, utilisateur2)
    );
    when(utilisateurService.listUserWithRole(role)).thenReturn(utilisateurDtos);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(
            APP_ROOT + "/utilisateur/findAllUtilisateurAvecRole/{role}",
            role
          )
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()").value(utilisateurDtos.size()))
      .andDo(print());
  }

  @Test
  void testFindByIdUtilisateur() throws Exception {}

  @Test
  @DisplayName("Test ajout de utilisateur")
  void testSaveUtilistateur() throws Exception {
    role.setCreationDate(Instant.now());
    role.setLastModifiedDate(Instant.now());
    role.setRoleName("ELEVE");
    role.setRoleDescription("ROLE eleves");
    //user
    utilisateur.setId(null);
    utilisateur.setCelUser("0708771317");
    utilisateur.setCreationDate(Instant.now());
    utilisateur.setGenreUser("M");
    utilisateur.setLastModifiedDate(Instant.now());
    utilisateur.setLoginUser("astairenazaire");
    utilisateur.setMailUser("astairenazaire@gmail.com");
    utilisateur.setMotDePasse("1234");
    utilisateur.setNomPUser("ZRANGO");
    utilisateur.setPrenomPrUser("NAZAIRE");
    utilisateur.setRoleName("ELEVE");
    utilisateur.setUrole(role);
    utilisateur.setTelMobileUser("0708771317");

    utilisateur2.setId(1L);
    utilisateur2.setCelUser("0708771317");
    utilisateur2.setCreationDate(Instant.now());
    utilisateur2.setGenreUser("M");
    utilisateur2.setLastModifiedDate(Instant.now());
    utilisateur2.setLoginUser("astairenazaire");
    utilisateur2.setMailUser("astairenazaire@gmail.com");
    utilisateur2.setMotDePasse("1234");
    utilisateur2.setNomPUser("ZRANGO");
    utilisateur2.setPrenomPrUser("NAZAIRE");
    utilisateur2.setRoleName("ELEVE");
    utilisateur2.setUrole(role);
    utilisateur2.setTelMobileUser("0708771317");

    JavaTimeModule javaTimeModule = new JavaTimeModule();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss"
    );
    javaTimeModule.addSerializer(
      LocalDateTime.class,
      new LocalDateTimeSerializer(dateTimeFormatter)
    );
    javaTimeModule.addDeserializer(
      LocalDateTime.class,
      new LocalDateTimeDeserializer(dateTimeFormatter)
    );
    om.registerModule(javaTimeModule);
    // UtilisateurDto uNewDto=om.readValue(new File("src/test/java/com/souclou/TestFile/newUser.json"), UtilisateurDto.class);
    // log.info("THE DTO BRING IS : {}", utilisateur);
    // // UtilisateurDto uReturnDto=om.readValue(new File("src/test/java/com/souclou/TestFile/uSave.json"), UtilisateurDto.class);
    Mockito.when(utilisateurService.saveOrUpdate(any(UtilisateurDto.class)))
      .thenReturn(utilisateur2);
    //verify(utilisateurService.saveOrUpdateUserRole(utilisateur));
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/utilisateur/saveOrUpdateUtilistateur")
          .content(om.writeValueAsString(utilisateur))
          .contentType(MediaType.APPLICATION_JSON)
         // .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.loginUser").value("astairenazaire"))
      .andDo(print());
  }

  @Test
  void testUpdateUtilistateur() throws Exception {}

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static String asJsoString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
