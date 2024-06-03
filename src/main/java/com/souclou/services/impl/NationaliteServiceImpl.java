package com.souclou.services.impl;

import com.souclou.dtos.NationaliteDto;
import com.souclou.entities.Nationalite;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.NationaliteRepository;
import com.souclou.services.NationaliteService;
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
public class NationaliteServiceImpl implements NationaliteService {

  final NationaliteRepository nationaliteRepository;
  final SouclouMapper souclouMapper;

  @Override
  public NationaliteDto saveOrUpdate(NationaliteDto dto) {
    return souclouMapper.fromNationalite(
      nationaliteRepository.save(souclouMapper.toNationalite(dto))
    );
  }

  @Override
  public boolean delete(Long id) {
    Nationalite nationalite = nationaliteRepository.findById(id).get();
    if (nationalite != null) {
      nationaliteRepository.delete(nationalite);
      return true;
    }

    return false;
  }

  @Override
  public NationaliteDto findById(Long id) {
    return souclouMapper.fromNationalite(
      nationaliteRepository.findById(id).get()
    );
  }

  @Override
  public List<NationaliteDto> findAll() {
     return nationaliteRepository
      .findAll(Sort.by(Sort.Direction.ASC,"descNat"))
      .stream()
      .map(x -> souclouMapper.fromNationalite(x))
      .collect(Collectors.toList());
  }
}
