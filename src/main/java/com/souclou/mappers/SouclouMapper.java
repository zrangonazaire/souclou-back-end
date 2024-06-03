package com.souclou.mappers;

import com.souclou.dtos.DepartementDto;
import com.souclou.dtos.EleveDto;
import com.souclou.dtos.NationaliteDto;
import com.souclou.dtos.RoleDto;
import com.souclou.dtos.UtilisateurDto;
import com.souclou.entities.Departement;
import com.souclou.entities.Eleve;
import com.souclou.entities.Nationalite;
import com.souclou.entities.Role;
import com.souclou.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
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

  public Utilisateur toUtilisateur(UtilisateurDto dto) {
    Utilisateur utilisateur = new Utilisateur();
    BeanUtils.copyProperties(dto, utilisateur);
    return utilisateur;
  }

  public UtilisateurDto fromUtilisateur(Utilisateur utilisateur) {
    UtilisateurDto utiliCreerOrUpdateUserDto = new UtilisateurDto();
    BeanUtils.copyProperties(utilisateur, utiliCreerOrUpdateUserDto);
    log.info(" THE ROLE IS NEXT DOOR {}", fromRole(utilisateur.getUrole()));
    return utiliCreerOrUpdateUserDto;
  }

  public Role toRole(RoleDto dto) {
    Role role = new Role();
    BeanUtils.copyProperties(dto, role);
    return role;
  }

  public RoleDto fromRole(Role dto) {
    RoleDto roleDto = new RoleDto();
    BeanUtils.copyProperties(dto, roleDto);

    return roleDto;
  }

  public NationaliteDto fromNationalite(Nationalite dto) {
    NationaliteDto nationaliteDto = new NationaliteDto();
    BeanUtils.copyProperties(dto, nationaliteDto);
    return nationaliteDto;
  }

  public Nationalite toNationalite(NationaliteDto dto) {
    Nationalite nationalite = new Nationalite();
    BeanUtils.copyProperties(dto, nationalite);
    return nationalite;
  }
  public Eleve toEleve(EleveDto dto){
    Eleve eleve=new Eleve();
    BeanUtils.copyProperties(dto, eleve);    
    return eleve;
  }
  public EleveDto fromEleve(Eleve dto){
    EleveDto eleveDto=new EleveDto();
    BeanUtils.copyProperties(dto, eleveDto);
    eleveDto.setIdNationalite(dto.getNationEleve().getId());
    return eleveDto;
  }
}
