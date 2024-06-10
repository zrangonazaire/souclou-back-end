package com.souclou.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Vocation extends AbstractEntity {

  String codeVocation;
  String libVocation;

  @OneToMany(mappedBy = "voParcour")
  List<Parcours> parcoursVocation;
}
