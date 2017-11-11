package com.blogspot.coderlife.motoads2.service.mapper;

import com.blogspot.coderlife.motoads2.domain.Brand;
import com.blogspot.coderlife.motoads2.service.dto.BrandDTO;
import com.blogspot.coderlife.motoads2.service.dto.ModelDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BrandDTOMapper {

  public static BrandDTO brandToBrandDTO(Brand brand) {
    List<ModelDTO> modelDTOs = brand.getModels()
        .stream()
        .map(ModelDTOMapper::modelToModelDTO)
        .collect(Collectors.toList());
    return BrandDTO.builder()
        .id(brand.getId())
        .name(brand.getName())
        .models(modelDTOs)
        .build();
  }
}