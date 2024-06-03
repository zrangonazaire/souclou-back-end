package com.souclou.error;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ItemNotFoundException extends RuntimeException {

  private Long id;

  public ItemNotFoundException(Long id) {
    super("Element introuvable avec l'ID" + id);
  }
}
