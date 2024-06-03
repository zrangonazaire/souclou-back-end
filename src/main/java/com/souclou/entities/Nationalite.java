package com.souclou.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Nationalite extends AbstractEntity {
  String descNat;
  @OneToMany(mappedBy="nationEleve")
  List<Eleve>eleves;
}
