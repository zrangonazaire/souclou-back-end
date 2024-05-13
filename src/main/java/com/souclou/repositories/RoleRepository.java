package com.souclou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.souclou.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
@Query("select r from Role r where r.roleName = ?1")
    Role findByRoleName(String roleName);

}
