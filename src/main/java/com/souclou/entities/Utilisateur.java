package com.souclou.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Utilisateur extends AbstractEntity {
  String nomPUser;
  String prenomPrUser;
  String telMobileUser;
  String celUser;
  String roleName;
  @Column(unique = true)
  String loginUser;

  String genreUser;
  String motDePasse;
  LocalDate dateDeNaissUser;

  @Column(unique = true)
  String mailUser;

  //SET USER ROLE
  @ManyToOne(fetch = FetchType.EAGER)
  Role urole;
}
