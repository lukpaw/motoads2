package com.blogspot.coderlife.motoads2.service;

import com.blogspot.coderlife.motoads2.domain.Brand;
import com.blogspot.coderlife.motoads2.repository.BrandRepository;
import com.blogspot.coderlife.motoads2.service.dto.BrandDTO;
import com.blogspot.coderlife.motoads2.service.mapper.BrandDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

  private final BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<BrandDTO> getBrands() {
    List<Brand> brands = brandRepository.findAll();
    return brands
        .stream()
        .map(BrandDTOMapper::brandToBrandDTO)
        .collect(Collectors.toList());
  }
}