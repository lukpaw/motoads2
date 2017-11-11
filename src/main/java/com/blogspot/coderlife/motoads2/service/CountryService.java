package com.blogspot.coderlife.motoads2.service;

import com.blogspot.coderlife.motoads2.domain.Country;
import com.blogspot.coderlife.motoads2.repository.CountryRepository;
import com.blogspot.coderlife.motoads2.service.dto.CountryDTO;
import com.blogspot.coderlife.motoads2.service.mapper.CountryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

  private final CountryRepository countryRepository;

  @Autowired
  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<CountryDTO> getCountries() {
    List<Country> countries = countryRepository.findAll();
    return countries
        .stream()
        .map(CountryDTOMapper::countryToCountryDTO)
        .collect(Collectors.toList());
  }
}
