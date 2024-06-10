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
public class Parcours  extends AbstractEntity{
    String libParcours;
    int niveauMin;
    int niveauMax;
    @ManyToOne
    Specialite specialParcours;
    @ManyToOne
    Vocation voParcour;
    @OneToMany(mappedBy = "niveauParc")
    List<Niveau>niveauParcours;

}
