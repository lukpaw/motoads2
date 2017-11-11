package com.blogspot.coderlife.motoads2.service.mapper;

import com.blogspot.coderlife.motoads2.domain.Country;
import com.blogspot.coderlife.motoads2.service.dto.CountryDTO;

public class CountryDTOMapper {
  public static CountryDTO countryToCountryDTO(Country country) {
    return CountryDTO.builder()
        .id(country.getId())
        .name(country.getName())
        .build();
  }
}
