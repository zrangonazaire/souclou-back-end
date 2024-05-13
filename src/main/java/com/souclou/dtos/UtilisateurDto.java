package com.souclou.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UtilisateurDto extends AbstractDto{

  @NotEmpty(message = "champs obligatoire")
  String nomPUser;

  String prenomPrUser;

  @NotEmpty(message = "Pas vide .")
  @NotNull(message = "Pas de valeur null")
  String telMobileUser;

  String celUser;

  @NotEmpty(message = "Pas vide .")
  @NotNull(message = "Pas de valeur null")
  String loginUser;

  String genreUser;
  String motDePasse;
  LocalDate dateDeNaissUser;
  String mailUser;

  @NotEmpty(message = "Pas vide .")
  @NotNull(message = "Pas de valeur null")
  String roleName;
  RoleDto urole;
}
