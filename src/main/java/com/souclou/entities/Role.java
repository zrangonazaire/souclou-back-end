package com.souclou.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role extends AbstractEntity{
    String roleName;
    String roleDescription;
     @OneToMany(mappedBy = "urole")
    List<Utilisateur> utilisateurs;
    
}
