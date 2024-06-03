package com.souclou.services.impl;

import com.souclou.dtos.EleveDto;
import com.souclou.entities.Eleve;
import com.souclou.entities.Nationalite;
import com.souclou.entities.Role;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.EleveRepository;
import com.souclou.repositories.NationaliteRepository;
import com.souclou.repositories.RoleRepository;
import com.souclou.services.EleveService;
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
public class EleveServiceImpl implements EleveService {

  final RoleRepository roleRepository;
  final EleveRepository eleveRepository;
  final SouclouMapper souclouMapper;
  final NationaliteRepository nationaliteRepository;

  @Override
  public EleveDto saveOrUpdate(EleveDto dto) {
    Role role = roleRepository.findByRoleName("ELEVE");
    Eleve elevCherche = eleveRepository.findById(dto.getId()).orElse(null);

    Nationalite nationalite = nationaliteRepository
      .findById(dto.getIdNationalite())
      .orElse(null);
    if (nationalite != null) {
      elevCherche.setNationEleve(nationalite);
    } else {
      throw new UnsupportedOperationException(
        "Unimplemented method 'saveOrUpdateUserRole'"
      );
    }
    if (role != null) {
      elevCherche.setUrole(role);
      elevCherche.setRoleName(role.getRoleName());
    } else {
      throw new UnsupportedOperationException(
        "Unimplemented method 'saveOrUpdateUserRole'"
      );
    }
    Eleve eleve = eleveRepository.save(elevCherche);
    return souclouMapper.fromEleve(eleve);
  }

  @Override
  public boolean delete(Long id) {
    Eleve eleve = eleveRepository.findById(id).get();
    if (!eleve.equals(null)) {
      eleveRepository.delete(eleve);
      return true;
    }
    return false;
  }

  @Override
  public EleveDto findById(Long id) {
    return souclouMapper.fromEleve(eleveRepository.findById(id).get());
  }

  @Override
  public List<EleveDto> findAll() {
    return eleveRepository
      .findAll(Sort.by(Sort.Direction.ASC, "nomUser"))
      .stream()
      .map(x -> souclouMapper.fromEleve(x))
      .collect(Collectors.toList());
  }
}
