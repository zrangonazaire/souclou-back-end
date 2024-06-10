package com.souclou.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Mention extends AbstractEntity {

  String libMention;

  @ManyToOne
  Departement depMention;

  @OneToMany(mappedBy = "mentionSpe")
  List<Specialite> specialMents;
}
