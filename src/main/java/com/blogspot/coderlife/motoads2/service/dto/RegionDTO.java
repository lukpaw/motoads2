package com.blogspot.coderlife.motoads2.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RegionDTO {
  private Long id;
  private String name;
}
