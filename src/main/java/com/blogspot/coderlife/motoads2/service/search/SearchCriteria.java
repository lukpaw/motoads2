package com.blogspot.coderlife.motoads2.service.search;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SearchCriteria {
  private String key;
  private String value;
}