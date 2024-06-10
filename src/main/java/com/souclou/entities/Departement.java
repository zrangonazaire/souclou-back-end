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
public class Departement extends AbstractEntity {

  String nomDepartement;
  String descDepartement;
  @OneToMany(mappedBy="depMention")
  List<Mention>mentionDeps;
}
