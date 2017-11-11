package com.blogspot.coderlife.motoads2.service.search;

import com.blogspot.coderlife.motoads2.domain.Advert;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class AdvertSpecificationsBuilder {
  private final List<SearchCriteria> params;

  public AdvertSpecificationsBuilder() {
    params = new ArrayList<>();
  }

  public void with(String key, String value) {
    params.add(new SearchCriteria(key, value));
  }

  public Specification<Advert> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<Advert>> specs = new ArrayList<>();
    for (SearchCriteria param : params) {
      specs.add(new AdvertSpecification(param));
    }

    Specification<Advert> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }
}