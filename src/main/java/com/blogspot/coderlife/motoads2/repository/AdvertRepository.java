package com.blogspot.coderlife.motoads2.repository;

import com.blogspot.coderlife.motoads2.domain.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertRepository extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {
}