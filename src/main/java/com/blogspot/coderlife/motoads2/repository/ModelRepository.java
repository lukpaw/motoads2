package com.blogspot.coderlife.motoads2.repository;

import com.blogspot.coderlife.motoads2.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
  List<Model> findAllByBrand_Id(Long brandId);
}