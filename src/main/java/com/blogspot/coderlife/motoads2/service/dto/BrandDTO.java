package com.blogspot.coderlife.motoads2.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BrandDTO {
  private Long id;
  private String name;
  private List<ModelDTO> models;
}
