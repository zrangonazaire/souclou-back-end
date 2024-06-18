package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import com.souclou.dtos.MentionDto;
import com.souclou.services.MentionService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APP_ROOT + "/mention")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentionController {

  final MentionService MentionService;

  //DELETE DEPARTEMENT API
  @DeleteMapping("/deleteMention/{id}")
  public ResponseEntity<Boolean> deleteMention(@PathVariable("id") Long id) {
    return ResponseEntity.ok(MentionService.delete(id));
  }

  //SAVE OR UPDATE API
  @PostMapping("/saveOrUpdateMention")
  public ResponseEntity<MentionDto> saveOrUpdateMention(
    @RequestBody MentionDto dto
  ) {
    return ResponseEntity.ok(MentionService.saveOrUpdate(dto));
  }

  //FIND BY ID DEPARTEMENT API
  @GetMapping("/findByIdMention/{id}")
  public ResponseEntity<MentionDto> findByIdMention(
    @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok(MentionService.findById(id));
  }

  @GetMapping("/findAllMention")
  public ResponseEntity<List<MentionDto>> findAllDMention() {
    return ResponseEntity.ok(MentionService.findAll());
  }
}
