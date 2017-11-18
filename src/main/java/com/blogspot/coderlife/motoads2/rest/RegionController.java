package com.blogspot.coderlife.motoads2.rest;

import com.blogspot.coderlife.motoads2.service.RegionService;
import com.blogspot.coderlife.motoads2.service.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/region")
public class RegionController {
  private final RegionService regionService;

  @Autowired
  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/{countryId}")
  public Collection<RegionDTO> getRegionsByCountryId(@PathVariable Long countryId) {
    return regionService.getRegionsByCountryId(countryId);
  }
}