package com.souclou.services;

import com.souclou.dtos.UtilisateurDto;

public interface UtilisateurService extends AbstractService<UtilisateurDto>{
    UtilisateurDto saveOrUpdateUserRole(UtilisateurDto dto);

}
