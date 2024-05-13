package com.souclou.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementDto extends AbstractDto{

  @NotEmpty(message = "Ce champ ne peut être vide!")
  @NotNull(message = "Ce champ ne peut être null!")
  private String nomDepartement;

  private String descDepartement;
}
