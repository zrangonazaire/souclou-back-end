package com.souclou.repositories;

import com.souclou.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository
  extends JpaRepository<Utilisateur, Long> {}
