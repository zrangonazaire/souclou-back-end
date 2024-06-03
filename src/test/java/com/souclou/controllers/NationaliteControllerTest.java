package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.souclou.dtos.NationaliteDto;
import com.souclou.services.NationaliteService;
import com.souclou.services.RoleService;
import java.time.Instant;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hamcrest.Matchers;
import org.junit.Before;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { NationaliteController.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NationaliteControllerTest {

  NationaliteDto nationaliteDto = new NationaliteDto();
  NationaliteDto nationaliteDto1 = new NationaliteDto();

  @MockBean
  NationaliteService nationaliteService;

  @Autowired
  private WebApplicationContext applicationContext;

  @MockBean
  private RoleService roleService;

  @Autowired
  private MockMvc mockMvc;

  @Before
  void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    doReturn(NationaliteDto.class)
      .when(nationaliteService)
      .saveOrUpdate(nationaliteDto);
    doReturn(NationaliteDto.class)
      .when(nationaliteService)
      .saveOrUpdate(nationaliteDto1);
  }

  @Test
  void notDeleteNationalite() throws Exception {
    long id = 3L;
    // when(utilisateurService).delete(id);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete(APP_ROOT + "/nationalite/deleteNationalite/{id}", id)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testDeleteNationite() throws Exception {
    long id = 1L;
    // when(utilisateurService).delete(id);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete(APP_ROOT + "/nationalite/deleteNationalite/{id}", id)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testFindAllNationalite() throws Exception {
    nationaliteDto.setCreationDate(Instant.now());
    nationaliteDto.setDescNat("Ivoirien");
    nationaliteDto.setId(1L);
    nationaliteDto.setIdCreateur(0L);
    nationaliteDto.setLastModifiedDate(Instant.now());

    nationaliteDto1.setCreationDate(Instant.now());
    nationaliteDto1.setDescNat("Malien");
    nationaliteDto1.setId(2L);
    nationaliteDto1.setIdCreateur(0L);
    nationaliteDto1.setLastModifiedDate(Instant.now());
    Mockito
      .when(nationaliteService.findAll())
      .thenReturn(Arrays.asList(nationaliteDto, nationaliteDto1));
    mockMvc
      .perform(
        MockMvcRequestBuilders.get(APP_ROOT + "/nationalite/findAllNationalite")
      )
      .andExpect(status().isOk())
      .andExpect(
        MockMvcResultMatchers
          .content()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
      )
      .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
      .andDo(print())
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.[0].descNat", Matchers.is("Ivoirien"))
      );
  }

  @Test
  void testFindByIdNationite() throws Exception {
    nationaliteDto1.setCreationDate(Instant.now());
    nationaliteDto1.setDescNat("Malien");
    nationaliteDto1.setId(1L);
    nationaliteDto1.setIdCreateur(0L);
    nationaliteDto1.setLastModifiedDate(Instant.now());
    Mockito.when(nationaliteService.findById(1L)).thenReturn(nationaliteDto1);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/nationalite/findByIdNationite/{id}", 1L)
          .accept(MediaType.APPLICATION_JSON_VALUE)
      )
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.descNat", Matchers.is("Malien"))
      );
  }

  @Test
  void testSaveOrUpdateNationite() throws Exception {}
}
