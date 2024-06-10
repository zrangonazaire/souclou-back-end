package com.souclou.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Niveau extends AbstractEntity {

  String libNiveau;
  int numGradeNiveau;
  @ManyToOne
  Parcours niveauParc;
}
