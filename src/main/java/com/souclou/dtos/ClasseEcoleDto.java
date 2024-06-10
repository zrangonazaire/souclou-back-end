package com.souclou.dtos;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClasseEcoleDto {

  String libClasse;
  boolean estOuvert;
  LocalDate dateOuverture;
  LocalDate dateFermeture;
  String anneeClasse;
}
