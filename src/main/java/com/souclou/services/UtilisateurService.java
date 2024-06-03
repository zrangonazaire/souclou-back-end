package com.souclou.services;

import java.util.List;

import com.souclou.dtos.UtilisateurDto;

public interface UtilisateurService extends AbstractService<UtilisateurDto>{
    UtilisateurDto saveOrUpdateUserRole(UtilisateurDto dto);
    List<UtilisateurDto>listUserWithRole(String role);

}
