package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import com.souclou.dtos.UtilisateurDto;
import com.souclou.services.UtilisateurService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APP_ROOT + "/utilisateur")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UtilisateurController {

  final UtilisateurService utilisateurService;

  @PostMapping("/saveOrUpdateUtilistateur")
  public ResponseEntity<UtilisateurDto> saveOrUpdateUtilistateur(
    @RequestBody UtilisateurDto dto
  ) {
    return ResponseEntity.ok(utilisateurService.saveOrUpdate(dto));
  }

  @DeleteMapping("/deleteUtilisateur/{id}")
  public ResponseEntity<Boolean> deleteUtilisateur(
    @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok(utilisateurService.delete(id));
  }

  @GetMapping("/findByIdUtilisateur/{id}")
  public ResponseEntity<UtilisateurDto> findByIdUtilisateur(
    @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok(utilisateurService.findById(id));
  }

  @GetMapping("/findAllUtilisateur")
  public ResponseEntity<List<UtilisateurDto>> findAllUtilisateur() {
    return ResponseEntity.ok(utilisateurService.findAll());
  }

  @GetMapping("/findAllUtilisateurAvecRole/{role}")
  public ResponseEntity<List<UtilisateurDto>> findAllUtilisateurAvecRole( @PathVariable("role") String role) {
    log.info(" THE ROLE IS NEXT DOOR IS ----- {}", role);
    return ResponseEntity.ok(utilisateurService.listUserWithRole(role));
  }
}
