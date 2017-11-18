package com.blogspot.coderlife.motoads2.service;

import com.blogspot.coderlife.motoads2.domain.Region;
import com.blogspot.coderlife.motoads2.repository.RegionRepository;
import com.blogspot.coderlife.motoads2.service.dto.RegionDTO;
import com.blogspot.coderlife.motoads2.service.mapper.RegionDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

  private final RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public List<RegionDTO> getRegionsByCountryId(Long countryId) {
    List<Region> regions = regionRepository.findAllByCountry_Id(countryId);
    return regions
        .stream()
        .map(RegionDTOMapper::regionToRegionDTO)
        .collect(Collectors.toList());
  }
}
