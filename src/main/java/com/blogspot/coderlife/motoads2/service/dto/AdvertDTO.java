package com.blogspot.coderlife.motoads2.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AdvertDTO {
  private Long id;
  private Long brandId;
  private String brandName;
  private Long modelId;
  private String modelName;
  private Long countryId;
  private String countryName;
  private Long regionId;
  private String regionName;
  private int year;
  private int price;
  private String imageUrl;
}