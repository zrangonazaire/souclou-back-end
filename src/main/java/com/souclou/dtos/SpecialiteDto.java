package com.souclou.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class SpecialiteDto extends AbstractDto {

  String libSpecialite;

  Long idMention;
}
