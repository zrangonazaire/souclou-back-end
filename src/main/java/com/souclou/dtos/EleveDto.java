package com.souclou.dtos;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class EleveDto extends UtilisateurDto {

  double montantScolarite;
  double ancienSolde;
  double nouveauSolde;
  String anneeScolaire;
  String serieBac;
  String anneBac;
  String nomPere;
  String nomMere;
  String etablissementOrigine;
  LocalDate dateInscriptionEleve;
  double remise;
  String mailEleve;
  String villeNaissance;
  String professionPere;
  String professionMere;
  String nomResponsable;
  String mailRespo;
  String telrespo1;
  String telRESpo2;
  String matriculeEtat;
  Long idNationalite;
}
