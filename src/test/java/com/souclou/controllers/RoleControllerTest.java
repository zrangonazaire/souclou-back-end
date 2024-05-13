package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.souclou.dtos.RoleDto;
import com.souclou.services.RoleService;
import java.time.Instant;
import java.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = { RoleController.class })
public class RoleControllerTest {

  RoleDto roleDto = new RoleDto();
  RoleDto roleDto1 = new RoleDto();

  @MockBean
  private RoleService roleService;

  @Autowired
  private WebApplicationContext applicationContext;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    roleDto.setId(1L);
    roleDto.setCreationDate(Instant.now());
    roleDto.setId(0L);
    roleDto.setIdCreateur(0L);
    roleDto.setLastModifiedDate(Instant.now());
    roleDto.setRoleDescription("Role professeur");
    roleDto.setRoleName("PROFESSEUR");

    roleDto1.setId(2L);
    roleDto1.setCreationDate(Instant.now());
    roleDto1.setId(0L);
    roleDto1.setIdCreateur(0L);
    roleDto1.setLastModifiedDate(Instant.now());
    roleDto1.setRoleDescription("Role Eleves");
    roleDto1.setRoleName("ELEVE");
    
  }

  @Test
  void testDeleteRole() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete(APP_ROOT + "/role/deleteRole/{id}", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  void shouldCreateMockMvn() {
    assertNotNull(mockMvc);
    assertNotNull(roleDto);
  }

  @Test
  @DisplayName("TEST D'AFFICHAGE DES ROLES ENREGISTRES")
  void testFindAllRole() throws Exception {
    Mockito
      .when(roleService.findAll())
      .thenReturn(Arrays.asList(roleDto, roleDto1));
    mockMvc
      .perform(MockMvcRequestBuilders.get(APP_ROOT + "/role/findAllRole"))
      .andExpect(status().isOk())
      .andExpect(
        MockMvcResultMatchers
          .content()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
      )
      .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
      // .andExpect(MockMvcResultMatchers.jsonPath("[0].id", Matchers.is(1)))
      // .andExpect(MockMvcResultMatchers.jsonPath("[0].roleName", Matchers.is("PROFESSEUR")))
      ;
  }

  @Test
  void testFindByIdRole() throws Exception {
    mockMvc
    .perform(
      MockMvcRequestBuilders
        .get(APP_ROOT + "/role/findByIdRole/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    )
    .andExpect(status().isOk())
    //.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
    ;
  }

  @Test
  void testSaveOrUpdateRole() throws Exception {
    String reqBody = objectMapper.writeValueAsString(roleDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/role/saveOrUpdateRole")
          .content(reqBody)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
    //.andExpect(MockMvcResultMatchers.jsonPath("roleName").exists());
  }
}
