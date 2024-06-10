package com.souclou.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class NiveauDto extends AbstractDto {

  String libNiveau;
  int numGradeNiveau;
  Long idNiveauParc;
}
