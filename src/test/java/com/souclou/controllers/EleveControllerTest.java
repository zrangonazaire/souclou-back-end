package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.souclou.dtos.EleveDto;
import com.souclou.services.EleveService;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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
@WebMvcTest(controllers = { EleveController.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EleveControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  EleveService eleveService;

  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  private WebApplicationContext applicationContext;
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
 @Before
  void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
 
  }
  @Test
  void testDeleteEleve() throws Exception {
    mockMvc
      .perform(MockMvcRequestBuilders.delete(APP_ROOT + "/eleve/deleteEleve/1"))
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testFindAllEleve() throws Exception {
    when(eleveService.findAll())
      .thenReturn(Arrays.asList(new EleveDto(), new EleveDto()));
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/eleve/findAllEleve")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2))
      .andDo(print());
  }

  @Test
  void testFindByIdEleve() throws Exception {
    EleveDto eleveDto = new EleveDto();
    eleveDto.setId(1L);
    when(eleveService.findById(1L)).thenReturn(eleveDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/eleve//findByIdEleve/1")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1))
      .andDo(print());
  }

  @Test
  void testCreateEleve() throws Exception {
    EleveDto eleveDto = new EleveDto();
    eleveDto.setNomPUser("koffi franck");
    when(eleveService.saveOrUpdate(any(EleveDto.class))).thenReturn(eleveDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/eleve/saveOrUpdateEleve")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(eleveDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.nomPUser").exists())
      .andExpect(jsonPath("$.nomPUser").value("koffi franck"))
      .andDo(print());
  }
  @Test
  void testUpdateEleve()throws Exception{
    EleveDto eleveDto = new EleveDto();
    eleveDto.setId(1L);
    eleveDto.setNomPUser("koffi franck");
    when(eleveService.findById(1L)).thenReturn(new EleveDto());
    when(eleveService.saveOrUpdate(any(EleveDto.class))).thenReturn(eleveDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/eleve/saveOrUpdateEleve")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(eleveDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.nomPUser").exists())
      .andExpect(jsonPath("$.nomPUser").value("koffi franck"))
      .andDo(print());
  }
}
