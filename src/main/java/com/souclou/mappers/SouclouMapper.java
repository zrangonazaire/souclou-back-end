package com.souclou.mappers;

import com.souclou.dtos.DepartementDto;
import com.souclou.entities.Departement;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SouclouMapper {

  public DepartementDto fromDepartement(Departement departement) {
    DepartementDto departementDto = new DepartementDto();
    BeanUtils.copyProperties(departement, departementDto);
    return departementDto;
  }
  public Departement toDepartement(DepartementDto departementDto) {
    Departement departement = new Departement();
    BeanUtils.copyProperties(departementDto, departement);
    return departement;
  }
}
