package com.blogspot.coderlife.motoads2.rest;

import com.blogspot.coderlife.motoads2.service.BrandService;
import com.blogspot.coderlife.motoads2.service.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/")
  public Collection<BrandDTO> getBrands() {
    return brandService.getBrands();
  }
}