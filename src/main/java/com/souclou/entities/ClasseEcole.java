package com.souclou.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ClasseEcole extends AbstractEntity {

  String libClasse;
  boolean estOuvert;
  LocalDate dateOuverture;
  LocalDate dateFermeture;
  @ManyToOne
  Annee anneeClasse;
}
