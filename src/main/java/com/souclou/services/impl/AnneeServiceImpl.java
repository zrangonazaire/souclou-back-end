package com.souclou.services.impl;

import com.souclou.dtos.AnneeDto;
import com.souclou.entities.Annee;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.AnneeRepository;
import com.souclou.services.AnneeService;
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
public class AnneeServiceImpl implements AnneeService {

  final SouclouMapper souclouMapper;
  final AnneeRepository anneeRepository;

  @Override
  public AnneeDto saveOrUpdate(AnneeDto dto) {
    Annee annee = anneeRepository.save(souclouMapper.toAnnee(dto));
    return souclouMapper.fromAnnee(annee);
  }

  @Override
  public boolean delete(Long id) {
     Annee annee = anneeRepository.findById(id).orElse(null);
    try {
      if (annee != null) {
        anneeRepository.delete(annee);
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
  public AnneeDto findById(Long id) {
    Annee annee = anneeRepository.findById(id).orElse(null);
    return souclouMapper.fromAnnee(annee);
  }

  @Override
  public List<AnneeDto> findAll() {
        return anneeRepository
      .findAll(Sort.by(Sort.Direction.DESC,"anneeScol"))
      .stream()
      .map(x -> souclouMapper.fromAnnee(x))
      .collect(Collectors.toList());
  }
}
