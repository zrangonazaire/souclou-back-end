package com.souclou.controllers;

import static com.souclou.constants.SecurityConstants.APP_ROOT;

import com.souclou.dtos.RoleDto;
import com.souclou.services.RoleService;
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
@RequestMapping(APP_ROOT + "/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    final RoleService roleService;

  @PostMapping("/saveOrUpdateRole")
  public ResponseEntity<RoleDto> saveOrUpdateRole(
    @RequestBody RoleDto dto
  ) {
    return ResponseEntity.ok(roleService.saveOrUpdate(dto));
  }

  @DeleteMapping("/deleteRole/{id}")
  public ResponseEntity<Boolean> deleteRole(
    @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok(roleService.delete(id));
  }

  @GetMapping("/findByIdRole/{id}")
  public ResponseEntity<RoleDto> findByIdRole(
    @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok(roleService.findById(id));
  }

  @GetMapping("/findAllRole")
  public ResponseEntity<List<RoleDto>> findAllRole() {
    return ResponseEntity.ok(roleService.findAll());
  }

}
