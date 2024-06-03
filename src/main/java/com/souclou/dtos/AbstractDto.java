package com.souclou.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

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
 // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  Instant creationDate;
  //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  Instant lastModifiedDate;

}
