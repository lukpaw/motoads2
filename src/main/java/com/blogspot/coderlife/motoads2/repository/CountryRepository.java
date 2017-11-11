package com.blogspot.coderlife.motoads2.repository;

import com.blogspot.coderlife.motoads2.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}