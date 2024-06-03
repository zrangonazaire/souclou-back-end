package com.souclou.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Getter
@Setter
public class Eleve extends Utilisateur{
    double montantScolarite;
    double ancienSolde;
    double nouveauSolde;
    String anneeScolaire;
    String serieBac;
    String anneBac;
    String nomPere;
    String nomMere;
    String etablissementOrigine;
    LocalDate dateInscriptionEleve;
    @ManyToOne
    Nationalite nationEleve;
    double remise;
    String mailEleve;
    String villeNaissance;
    String professionPere;
    String professionMere;
    String nomResponsable;
    String mailRespo;
    String telrespo1;
    String telRESpo2;
    String matriculeEtat;
}
