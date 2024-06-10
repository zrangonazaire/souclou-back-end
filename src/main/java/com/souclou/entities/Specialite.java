package com.souclou.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Specialite  extends AbstractEntity{
    String libSpecialite;
    @ManyToOne
    Mention mentionSpe;
    @OneToMany(mappedBy = "specialParcours")
    List<Parcours>parcourSpace;
}
