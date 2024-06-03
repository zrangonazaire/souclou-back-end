package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souclou.dtos.NationaliteDto;
import com.souclou.services.NationaliteService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(APP_ROOT + "/nationalite")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NationaliteController {
    final NationaliteService nationaliteService;
      //DELETE DEPARTEMENT API
  @DeleteMapping("/deleteNationalite/{id}")
  public ResponseEntity<Boolean> deleteNationite(
    @PathVariable("id") Long id
  ) {
    log.info(" THE ID ; {}", id);
    return ResponseEntity.ok(nationaliteService.delete(id));
  }
//SAVE OR UPDATE API
  @PostMapping("/saveOrUpdateNationite")
  public ResponseEntity<NationaliteDto> saveOrUpdateNationite(
    @RequestBody NationaliteDto dto
  ) {
    return ResponseEntity.ok(nationaliteService.saveOrUpdate(dto));
  }
  //FIND BY ID DEPARTEMENT API
  @GetMapping("/findByIdNationite/{id}")
  public ResponseEntity<NationaliteDto>findByIdNationite(  @PathVariable("id") Long id){
    return ResponseEntity.ok(nationaliteService.findById(id));
  }
  @GetMapping("/findAllNationalite")
  public ResponseEntity<List<NationaliteDto>> findAllDNationalite(){
    return ResponseEntity.ok(nationaliteService.findAll());
  }

}
