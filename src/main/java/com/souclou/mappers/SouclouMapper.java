package com.souclou.mappers;

import com.souclou.dtos.AnneeDto;
import com.souclou.dtos.ClasseEcoleDto;
import com.souclou.dtos.DepartementDto;
import com.souclou.dtos.EleveDto;
import com.souclou.dtos.MentionDto;
import com.souclou.dtos.NationaliteDto;
import com.souclou.dtos.NiveauDto;
import com.souclou.dtos.ParcoursDto;
import com.souclou.dtos.RoleDto;
import com.souclou.dtos.SpecialiteDto;
import com.souclou.dtos.UtilisateurDto;
import com.souclou.dtos.VocationDto;
import com.souclou.entities.Annee;
import com.souclou.entities.ClasseEcole;
import com.souclou.entities.Departement;
import com.souclou.entities.Eleve;
import com.souclou.entities.Mention;
import com.souclou.entities.Nationalite;
import com.souclou.entities.Niveau;
import com.souclou.entities.Parcours;
import com.souclou.entities.Role;
import com.souclou.entities.Specialite;
import com.souclou.entities.Utilisateur;
import com.souclou.entities.Vocation;
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

  public Eleve toEleve(EleveDto dto) {
    Eleve eleve = new Eleve();
    BeanUtils.copyProperties(dto, eleve);
    return eleve;
  }

  public EleveDto fromEleve(Eleve dto) {
    EleveDto eleveDto = new EleveDto();
    BeanUtils.copyProperties(dto, eleveDto);
    eleveDto.setIdNationalite(dto.getNationEleve().getId());
    return eleveDto;
  }

  public Annee toAnnee(AnneeDto dto) {
    Annee annee = new Annee();
    BeanUtils.copyProperties(dto, annee);
    return annee;
  }

  public AnneeDto fromAnnee(Annee dto) {
    AnneeDto annee = new AnneeDto();
    BeanUtils.copyProperties(dto, annee);
    return annee;
  }

  public ClasseEcoleDto fromClasseEcole(ClasseEcole dto) {
    ClasseEcoleDto classeEcoleDto = new ClasseEcoleDto();
    BeanUtils.copyProperties(dto, classeEcoleDto);
    classeEcoleDto.setAnneeClasse(dto.getAnneeClasse().getAnneeScol());
    return classeEcoleDto;
  }

  public ClasseEcole toClasseEcole(ClasseEcoleDto dto) {
    ClasseEcole classeEcole = new ClasseEcole();
    BeanUtils.copyProperties(dto, classeEcole);
    // classeEcole.setAnneeClasse(dto.getAnneeClasse().getAnneeScol());
    return classeEcole;
  }

  public Mention toMention(MentionDto dto) {
    Mention mention = new Mention();
    BeanUtils.copyProperties(dto, mention);
    return mention;
  }

  public MentionDto fromMention(Mention dto) {
    MentionDto mentionDto = new MentionDto();
    BeanUtils.copyProperties(dto, mentionDto);
    mentionDto.setIdDepart(dto.getDepMention().getId());
    return mentionDto;
  }

  public Niveau toNiveau(NiveauDto dto) {
    Niveau niveau = new Niveau();
    BeanUtils.copyProperties(dto, niveau);
    return niveau;
  }

  public NiveauDto fromNiveau(Niveau dto) {
    NiveauDto niveauDto = new NiveauDto();
    BeanUtils.copyProperties(dto, niveauDto);
    niveauDto.setIdNiveauParc(dto.getNiveauParc().getId());
    return niveauDto;
  }

  public ParcoursDto fromParcours(Parcours dto) {
    ParcoursDto parcoursDto = new ParcoursDto();
    BeanUtils.copyProperties(dto, parcoursDto);
    parcoursDto.setIdSpecialParcours(dto.getSpecialParcours().getId());
    parcoursDto.setIdVocParcour(dto.getVoParcour().getId());
    return parcoursDto;
  }

  public Parcours toParcours(ParcoursDto dto) {
    Parcours parcours = new Parcours();
    BeanUtils.copyProperties(dto, parcours);
    return parcours;
  }

  public Specialite toSpecialite(SpecialiteDto dto) {
    Specialite specialite = new Specialite();
    BeanUtils.copyProperties(dto, specialite);
    return specialite;
  }

  public SpecialiteDto fromSpecialite(Specialite dto) {
    SpecialiteDto specialiteDto = new SpecialiteDto();
    BeanUtils.copyProperties(dto, specialiteDto);
    specialiteDto.setIdMention(dto.getMentionSpe().getId());
    return specialiteDto;
  }

  public VocationDto fromVocation(Vocation dto) {
    VocationDto vocationDto = new VocationDto();
    BeanUtils.copyProperties(dto, vocationDto);
    return vocationDto;
  }

  public Vocation toVocation(VocationDto dto) {
    Vocation vocation = new Vocation();
    BeanUtils.copyProperties(dto, vocation);
    return vocation;
  }
}
