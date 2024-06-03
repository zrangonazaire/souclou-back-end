package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import com.souclou.dtos.EleveDto;
import com.souclou.services.EleveService;
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
@RequestMapping(APP_ROOT + "/eleve")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EleveController {

  final EleveService eleveService;

  @PostMapping("/saveOrUpdateEleve")
  public ResponseEntity<EleveDto> saveOrUpdateEleve(@RequestBody EleveDto dto) {
    return ResponseEntity.ok(eleveService.saveOrUpdate(dto));
  }

  @DeleteMapping("/deleteEleve/{id}")
  public ResponseEntity<Boolean> deleteEleve(@PathVariable("id") Long id) {
    return ResponseEntity.ok(eleveService.delete(id));
  }

  @GetMapping("/findByIdEleve/{id}")
  public ResponseEntity<EleveDto> findByIdEleve(@PathVariable("id") Long id) {
    return ResponseEntity.ok(eleveService.findById(id));
  }

  @GetMapping("/findAllEleve")
  public ResponseEntity<List<EleveDto>> findAllEleve() {
    return ResponseEntity.ok(eleveService.findAll());
  }
}
