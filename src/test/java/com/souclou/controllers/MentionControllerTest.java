package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souclou.dtos.EleveDto;
import com.souclou.dtos.MentionDto;
import com.souclou.services.MentionService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { MentionController.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentionControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MentionService mentionService;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Before
  void before() {
    mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testDeleteMention() throws Exception{
  mockMvc
      .perform(MockMvcRequestBuilders.delete(APP_ROOT + "/mention/deleteMention/1"))
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void testFindAllDMention() throws Exception{
      when(mentionService.findAll())
      .thenReturn(Arrays.asList(new MentionDto(), new MentionDto()));
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/mention/findAllMention")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2))
      .andDo(print());
  }

  @Test
  void testFindByIdMention() throws Exception {
    MentionDto mentionDto = new MentionDto();
    mentionDto.setId(1L);
    when(mentionService.findById(1L)).thenReturn(mentionDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get(APP_ROOT + "/mention//findByIdMention/1")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1))
      .andDo(print());
  }

  @Test
  void testSaveMention() throws Exception {
     MentionDto mentionDto = new MentionDto();
     mentionDto.setLibMention("GENERALE");
    when(mentionService.saveOrUpdate(any(MentionDto.class))).thenReturn(mentionDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/mention/saveOrUpdateMention")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(mentionDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.libMention").exists())
      .andExpect(jsonPath("$.libMention").value("GENERALE"))
      .andDo(print());
  }

  @Test
  void testUpdateMention() throws Exception {
    MentionDto mentionDto = new MentionDto();
    mentionDto.setId(1L);
    mentionDto.setLibMention("GENERALE");
    when(mentionService.findById(1L)).thenReturn(new MentionDto());
    when(mentionService.saveOrUpdate(any(MentionDto.class))).thenReturn(mentionDto);
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(APP_ROOT + "/mention/saveOrUpdateMention")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(mentionDto))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.libMention").exists())
      .andExpect(jsonPath("$.libMention").value("GENERALE"))
      .andDo(print());
  }
}
