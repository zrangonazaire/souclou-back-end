package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.souclou.dtos.AnneeDto;
import com.souclou.services.AnneeService;
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
@WebMvcTest(controllers = { AnneeController.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnneeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  AnneeService anneeService;

  @Autowired
  private WebApplicationContext applicationContext;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Before
  void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
  }

  @Test
  void testDeleteAnnee() throws Exception {
    mockMvc
      .perform(MockMvcRequestBuilders.delete(APP_ROOT + "/annee/deleteAnnee/1"))
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testFindAllAnnee() throws Exception {
    when(anneeService.findAll())
      .thenReturn(Arrays.asList(new AnneeDto(), new AnneeDto()));
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/annee/findAllAnnee")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2))
      .andDo(print());
  }

  @Test
  void testFindByIdAnnee() throws Exception {
    AnneeDto anneeDto = new AnneeDto();
    anneeDto.setId(1L);
    when(anneeService.findById(1L)).thenReturn(anneeDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/annee//findByIdAnnee/1")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1))
      .andDo(print());
  }

  @Test
  void testSaveAnnee() throws Exception {
    AnneeDto anneeDto = new AnneeDto();
    anneeDto.setAnneeScol("2024/2025");
    when(anneeService.saveOrUpdate(any(AnneeDto.class))).thenReturn(anneeDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/annee/saveOrUpdateAnnee")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(anneeDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.anneeScol").exists())
      .andExpect(jsonPath("$.anneeScol").value("2024/2025"))
      .andDo(print());
  }

  @Test
  void testUpdateAnnee() throws Exception {
    AnneeDto anneeDto = new AnneeDto();
    anneeDto.setId(1L);
    anneeDto.setAnneeScol("2023/2024");
    when(anneeService.findById(1L)).thenReturn(new AnneeDto());
    when(anneeService.saveOrUpdate(any(AnneeDto.class))).thenReturn(anneeDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/annee/saveOrUpdateAnnee")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(anneeDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.anneeScol").exists())
      .andExpect(jsonPath("$.anneeScol").value("2023/2024"))
      .andDo(print());
  }
}
