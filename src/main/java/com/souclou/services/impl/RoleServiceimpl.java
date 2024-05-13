package com.souclou.services.impl;

import com.souclou.dtos.RoleDto;
import com.souclou.entities.Role;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.RoleRepository;
import com.souclou.services.RoleService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceimpl implements RoleService {
final RoleRepository roleRepository;
final SouclouMapper souclouMapper;
  @Override
  public RoleDto saveOrUpdate(RoleDto dto) {
   Role role=roleRepository.save(souclouMapper.toRole(dto));
    return souclouMapper.fromRole(role);
  }

  @Override
  public boolean delete(Long id) {
    Role role=roleRepository.findById(id).get();
    if (role!=null) {
        roleRepository.delete(role);
        return true;
    }
   return false;
  }

  @Override
  public RoleDto findById(Long id) {
    Role role=roleRepository.findById(id).get();
   return souclouMapper.fromRole(role);
    
  }

  @Override
  public List<RoleDto> findAll() {
  return roleRepository
      .findAll(Sort.by(Sort.Direction.ASC,"roleName"))
      .stream()
      .map(x -> souclouMapper.fromRole(x))
      .collect(Collectors.toList());
  }
}
