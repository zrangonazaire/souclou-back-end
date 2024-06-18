package com.souclou.services.impl;

import com.souclou.dtos.DepartementDto;
import com.souclou.dtos.MentionDto;
import com.souclou.entities.Mention;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.MentionRepository;
import com.souclou.services.DepartementService;
import com.souclou.services.MentionService;
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
public class MentionServiceImpl implements MentionService {

  final MentionRepository mentionRepository;
  final DepartementService departementService;
  final SouclouMapper souclouMapper;

  @Override
  public MentionDto saveOrUpdate(MentionDto dto) {
    DepartementDto departementDto = departementService.findById(
      dto.getIdDepart()
    );
    Mention mention = souclouMapper.toMention(dto);
    if (departementDto != null) {
      mention.setDepMention(souclouMapper.toDepartement(departementDto));
    } else {
      throw new UnsupportedOperationException(
        "Unimplemented method 'saveOrUpdate'"
      );
    }
    Mention mentionSave = mentionRepository.save(mention);
    return souclouMapper.fromMention(mentionSave);
  }

  @Override
  public boolean delete(Long id) {
    Mention mention = mentionRepository.findById(id).orElse(null);
    try {
      if (mention != null) {
        mentionRepository.delete(mention);
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
  public MentionDto findById(Long id) {
    Mention mention = mentionRepository.findById(id).orElse(null);
    return souclouMapper.fromMention(mention);
  }

  @Override
  public List<MentionDto> findAll() {
   return  mentionRepository.findAll(Sort.by(Sort.Direction.ASC,"libMention"))
    .stream()
    .map(x -> souclouMapper.fromMention(x))
    .collect(Collectors.toList());
  }
}
