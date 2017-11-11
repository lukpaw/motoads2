package com.blogspot.coderlife.motoads2.service.mapper;

import com.blogspot.coderlife.motoads2.domain.Model;
import com.blogspot.coderlife.motoads2.service.dto.ModelDTO;

class ModelDTOMapper {

  public static ModelDTO modelToModelDTO(Model model) {
    return ModelDTO.builder()
        .id(model.getId())
        .name(model.getName())
        .build();
  }
}