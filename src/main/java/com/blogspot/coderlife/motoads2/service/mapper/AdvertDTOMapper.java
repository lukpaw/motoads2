package com.blogspot.coderlife.motoads2.service.mapper;

import com.blogspot.coderlife.motoads2.domain.Advert;
import com.blogspot.coderlife.motoads2.domain.Model;
import com.blogspot.coderlife.motoads2.domain.Region;
import com.blogspot.coderlife.motoads2.service.dto.AdvertDTO;

public class AdvertDTOMapper {

  public static AdvertDTO advertToAdvertDTO(Advert advert) {
    return AdvertDTO.builder()
        .id(advert.getId())
        .brandId(advert.getModel().getBrand().getId())
        .brandName(advert.getModel().getBrand().getName())
        .modelId(advert.getModel().getId())
        .modelName(advert.getModel().getName())
        .countryId(advert.getRegion().getCountry().getId())
        .countryName(advert.getRegion().getCountry().getName())
        .regionId(advert.getRegion().getId())
        .regionName(advert.getRegion().getName())
        .year(advert.getYear())
        .price(advert.getPrice())
        .imageUrl(advert.getImageUrl())
        .build();
  }

  public static Advert advertDTOToAdvert(AdvertDTO advertDTO) {
    // TODO Check better way to init model
    Model model = new Model();
    model.setId(advertDTO.getModelId());
    // TODO Check better way to init region
    Region region = new Region();
    region.setId(advertDTO.getRegionId());

    return Advert.builder()
        .id(advertDTO.getId())
        .model(model)
        .region(region)
        .year(advertDTO.getYear())
        .price(advertDTO.getPrice())
        .imageUrl(advertDTO.getImageUrl())
        .build();
  }
}