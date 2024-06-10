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

import com.souclou.dtos.AnneeDto;
import com.souclou.services.AnneeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(APP_ROOT + "/annee")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnneeController {
    final AnneeService anneeService;

  //DELETE Annee API
  @DeleteMapping("/deleteAnnee/{id}")
  public ResponseEntity<Boolean> deleteAnnee(
    @PathVariable("id") Long id
  ) {
    log.info(" THE ID ; {}", id);
    return ResponseEntity.ok(anneeService.delete(id));
  }
//SAVE OR UPDATE API
  @PostMapping("/saveOrUpdateAnnee")
  public ResponseEntity<AnneeDto> saveOrUpdateAnnee(
    @RequestBody AnneeDto dto
  ) {
    return ResponseEntity.ok(anneeService.saveOrUpdate(dto));
  }
  //FIND BY ID Annee API
  @GetMapping("/findByIdAnnee/{id}")
  public ResponseEntity<AnneeDto>findByIdAnnee(  @PathVariable("id") Long id){
    return ResponseEntity.ok(anneeService.findById(id));
  }
  @GetMapping("/findAllAnnee")
  public ResponseEntity<List<AnneeDto>> findAllAnnee(){
    return ResponseEntity.ok(anneeService.findAll());
  }

}
