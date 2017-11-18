package com.blogspot.coderlife.motoads2.service;

import com.blogspot.coderlife.motoads2.domain.Model;
import com.blogspot.coderlife.motoads2.repository.ModelRepository;
import com.blogspot.coderlife.motoads2.service.dto.ModelDTO;
import com.blogspot.coderlife.motoads2.service.mapper.ModelDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {
  private final ModelRepository modelRepository;

  @Autowired
  public ModelService(ModelRepository modelRepository) {
    this.modelRepository = modelRepository;
  }

  public List<ModelDTO> getModelsByBrandId(Long brandId) {
    List<Model> models = modelRepository.findAllByBrand_Id(brandId);
    return models
        .stream()
        .map(ModelDTOMapper::modelToModelDTO)
        .collect(Collectors.toList());

  }

}
