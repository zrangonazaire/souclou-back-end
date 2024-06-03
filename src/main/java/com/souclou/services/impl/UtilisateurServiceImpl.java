package com.souclou.services.impl;

import com.souclou.dtos.UtilisateurDto;
import com.souclou.entities.Role;
import com.souclou.entities.Utilisateur;
import com.souclou.mappers.SouclouMapper;
import com.souclou.repositories.RoleRepository;
import com.souclou.repositories.UtilisateurRepository;
import com.souclou.services.UtilisateurService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  final RoleRepository roleRepository;
  final SouclouMapper souclouMapper;
  final UtilisateurRepository utilisateurRepository;


  @Override
  public UtilisateurDto saveOrUpdate(UtilisateurDto dto) {
    Role role = roleRepository.findByRoleName(dto.getRoleName());
    Utilisateur userCherche = utilisateurRepository.findById(dto.getId()).orElse(null);
    if (userCherche==null) {
      userCherche=souclouMapper.toUtilisateur(dto);
    }
    if (role != null) {
      userCherche.setUrole(role);
      userCherche.setRoleName(role.getRoleName());
    } else {
      throw new UnsupportedOperationException(
        "Unimplemented method 'saveOrUpdateUserRole'"
      );
    }
    Utilisateur user = utilisateurRepository.save(userCherche);
    return souclouMapper.fromUtilisateur(user);
  }

  @Override
  public boolean delete(Long id) {
    Utilisateur utilisateur = utilisateurRepository.findById(id).get();
    if (!utilisateur.equals(null)) {
      utilisateurRepository.delete(utilisateur);
      return true;
    }
    return false;
  }

  @Override
  public UtilisateurDto findById(Long id) {
    return souclouMapper.fromUtilisateur(
      utilisateurRepository.findById(id).get()
    );
  }

  @Override
  public List<UtilisateurDto> findAll() {
    return utilisateurRepository
      .findAll(Sort.by(Sort.Direction.ASC, "nomUser"))
      .stream()
      .map(x -> souclouMapper.fromUtilisateur(x))
      .collect(Collectors.toList());
  }

  @Override
  public UtilisateurDto saveOrUpdateUserRole(UtilisateurDto dto) {
  //  Role role = roleRepository.findByRoleName(dto.getRoleName());

    throw new UnsupportedOperationException(
      "Unimplemented method 'saveOrUpdateUserRole'"
    );
  }

  @Override
  public List<UtilisateurDto> listUserWithRole(String role) {
  log.info("role is  {}",role);
    return utilisateurRepository
      .findAll(Sort.by(Sort.Direction.ASC, "roleName"))
      .stream()
      .filter(x->x.getUrole().getRoleName().contains(role))
      .map(x -> souclouMapper.fromUtilisateur(x))
      .collect(Collectors.toList());
  }
}
