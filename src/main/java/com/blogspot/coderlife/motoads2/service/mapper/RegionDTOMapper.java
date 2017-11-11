package com.blogspot.coderlife.motoads2.service.mapper;

import com.blogspot.coderlife.motoads2.domain.Region;
import com.blogspot.coderlife.motoads2.service.dto.RegionDTO;

public class RegionDTOMapper {
  public static RegionDTO regionToRegionDTO(Region Region) {
    return RegionDTO.builder()
        .id(Region.getId())
        .name(Region.getName())
        .build();
  }
}