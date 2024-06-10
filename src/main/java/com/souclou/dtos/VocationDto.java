package com.souclou.dtos;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class VocationDto extends AbstractDto {
    String codeVocation;
    String libVocation;
}
