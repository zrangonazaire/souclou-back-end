package com.souclou.services;

import java.util.List;

public interface AbstractService<T> {
  T saveOrUpdate(T dto);
  boolean delete(Long id);
  T findById(Long id);
  List<T> findAll();
}
