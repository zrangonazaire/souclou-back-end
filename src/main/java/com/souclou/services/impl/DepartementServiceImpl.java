package com.souclou.services.impl;

import com.souclou.dtos.DepartementDto;
import com.souclou.entities.Departement;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.DepartementRepository;
import com.souclou.services.DepartementService;

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
public class DepartementServiceImpl implements DepartementService {

  final DepartementRepository departementRepository;
  final SouclouMapper souclouMapper;

  @Override
  public DepartementDto saveOrUpdate(DepartementDto dto) {
    Departement departement = departementRepository
      .findById(dto.getId())
      .orElse(null);

    if (departement != null) {
      departement.setNomDepartement(dto.getNomDepartement());
      departement.setDescDepartement(dto.getDescDepartement());
      Departement saveDepartement = departementRepository.saveAndFlush(
        departement
      );
      return souclouMapper.fromDepartement(saveDepartement);
    }
    Departement depNew = new Departement();
    depNew.setNomDepartement(dto.getNomDepartement());
    depNew.setDescDepartement(dto.getDescDepartement());

    Departement saveDepartement = departementRepository.saveAndFlush(depNew);
    return souclouMapper.fromDepartement(saveDepartement);
  }

  @Override
  public boolean delete(Long id) {
    Departement departement = departementRepository.findById(id).orElse(null);
    try {
      if (departement != null) {
        departementRepository.delete(departement);
        return true;
      }
      {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public DepartementDto findById(Long id) {
    Departement departement = departementRepository.findById(id).orElse(null);
    return souclouMapper.fromDepartement(departement);
  }

  @Override
  public List<DepartementDto> findAll() {
    return departementRepository
      .findAll(Sort.by(Sort.Direction.ASC,"nomDepartement"))
      .stream()
      .map(x -> souclouMapper.fromDepartement(x))
      .collect(Collectors.toList());
  }
}
