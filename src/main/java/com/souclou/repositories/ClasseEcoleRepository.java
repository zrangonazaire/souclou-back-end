package com.souclou.repositories;

import com.souclou.entities.ClasseEcole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseEcoleRepository
  extends JpaRepository<ClasseEcole, Long> {}
