package com.souclou.dtos;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractDto {
  Long id;
  Long idCreateur;
  Instant creationDate;
  Instant lastModifiedDate;

}
