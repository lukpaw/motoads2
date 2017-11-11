package com.blogspot.coderlife.motoads2.rest;

import com.blogspot.coderlife.motoads2.service.CountryService;
import com.blogspot.coderlife.motoads2.service.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/country")
public class CountryController {
  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/")
  public Collection<CountryDTO> getCountries() {
    return countryService.getCountries();
  }
}