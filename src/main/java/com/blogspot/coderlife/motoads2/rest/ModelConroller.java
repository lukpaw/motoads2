package com.blogspot.coderlife.motoads2.rest;

import com.blogspot.coderlife.motoads2.service.ModelService;
import com.blogspot.coderlife.motoads2.service.dto.ModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/model")
public class ModelConroller {
  private final ModelService modelService;

  @Autowired
  public ModelConroller(ModelService modelService) {
    this.modelService = modelService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/{brandId}")
  public Collection<ModelDTO> getModelsByBrandId(@PathVariable Long brandId) {
    return modelService.getModelsByBrandId(brandId);
  }
}
