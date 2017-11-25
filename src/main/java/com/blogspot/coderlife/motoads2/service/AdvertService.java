package com.blogspot.coderlife.motoads2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.blogspot.coderlife.motoads2.domain.Advert;
import com.blogspot.coderlife.motoads2.repository.AdvertRepository;
import com.blogspot.coderlife.motoads2.service.dto.AdvertDTO;
import com.blogspot.coderlife.motoads2.service.mapper.AdvertDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertService {

  //private static final Logger log = LogManager.getLogger(AdvertService.class);
  private final AdvertRepository advertRepository;

  @Autowired
  public AdvertService(AdvertRepository advertRepository) {
    this.advertRepository = advertRepository;
  }

  public List<AdvertDTO> getAdverts() {
    List<Advert> adverts = advertRepository.findAll();
    return adverts
        .stream()
        .map(AdvertDTOMapper::advertToAdvertDTO)
        .collect(Collectors.toList());
  }

  public void addAdvert(AdvertDTO newAdvertDTO) {
    Advert newAdvert = AdvertDTOMapper.advertDTOToAdvert(newAdvertDTO);
    advertRepository.save(newAdvert);
    // TODO Maybe return added advert
  }

  public List<AdvertDTO> getAdverts(Specification<Advert> spec) {
    List<Advert> adverts = advertRepository.findAll(spec);
    return adverts
        .stream()
        .map(AdvertDTOMapper::advertToAdvertDTO)
        .collect(Collectors.toList());
  }
}