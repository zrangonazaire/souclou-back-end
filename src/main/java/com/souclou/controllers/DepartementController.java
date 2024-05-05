package com.souclou.controllers;

import java.util.List;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import com.souclou.dtos.DepartementDto;
import com.souclou.services.DepartementService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APP_ROOT + "/departement")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartementController {

  final DepartementService departementService;

  //DELETE DEPARTEMENT API
  @PostMapping("/deleteDepartement/{id}")
  public ResponseEntity<Boolean> deleteDepartement(
    @PathVariable("id") Long id
  ) {
    log.info(" THE ID ; {}", id);
    return ResponseEntity.ok(departementService.delete(id));
  }
//SAVE OR UPDATE API
  @PostMapping("/saveOrUpdateDepartement")
  public ResponseEntity<DepartementDto> saveOrUpdateDepartement(
    @RequestBody DepartementDto dto
  ) {
    return ResponseEntity.ok(departementService.saveOrUpdate(dto));
  }
  //FIND BY ID DEPARTEMENT API
  @GetMapping("/findByIdDepartement/{id}")
  public ResponseEntity<DepartementDto>findByIdDepartement(  @PathVariable("id") Long id){
    return ResponseEntity.ok(departementService.findById(id));
  }
  @GetMapping("/findAllDepartement")
  public ResponseEntity<List<DepartementDto>> findAllDepartement(){
    return ResponseEntity.ok(departementService.findAll());
  }
}
