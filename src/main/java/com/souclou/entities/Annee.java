package com.souclou.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Annee extends AbstractEntity {

  String anneeScol;
  @OneToMany(mappedBy="anneeClasse")
  List<ClasseEcole>annClasseEcoles;
}
