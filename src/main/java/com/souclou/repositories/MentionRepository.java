package com.souclou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souclou.entities.Mention;

public interface MentionRepository extends JpaRepository<Mention,Long>{

}
