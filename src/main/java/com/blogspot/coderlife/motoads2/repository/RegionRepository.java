package com.blogspot.coderlife.motoads2.repository;

import com.blogspot.coderlife.motoads2.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

  List<Region> findAllByCountry_Id(Long countryId);
}
