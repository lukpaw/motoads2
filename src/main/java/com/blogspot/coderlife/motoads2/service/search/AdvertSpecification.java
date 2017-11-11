package com.blogspot.coderlife.motoads2.service.search;

import com.blogspot.coderlife.motoads2.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AdvertSpecification implements Specification<Advert> {
  private final SearchCriteria criteria;

  public AdvertSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    if (criteria.getKey().equalsIgnoreCase("sortBy")) {
      query.orderBy(builder.asc(root.get(criteria.getValue())));
      return null;
    }
    else if (criteria.getKey().equalsIgnoreCase("brandId")) {
      Subquery<Model> subQuery = query.subquery(Model.class);
      Root<Model> subRoot = subQuery.from(Model.class);
      subQuery.select(subRoot);

      Predicate modelPredicate = builder.equal(root.get("model"), subRoot.get("id"));

      Brand brand = new Brand();
      brand.setId(new Long(criteria.getValue()));
      Predicate brandPredicate = builder.equal(subRoot.get("brand"), brand);

      subQuery.select(subRoot).where(modelPredicate, brandPredicate);
      return builder.exists(subQuery);
    }
    else if (criteria.getKey().equalsIgnoreCase("modelId")) {
      Model model = new Model();
      model.setId(new Long(criteria.getValue()));
      return builder.equal(root.get("model"), model);
    }
    else if (criteria.getKey().equalsIgnoreCase("countryId")) {
      Subquery<Region> subQuery = query.subquery(Region.class);
      Root<Region> subRoot = subQuery.from(Region.class);
      subQuery.select(subRoot);

      Predicate regionPredicate = builder.equal(root.get("region"), subRoot.get("id"));

      Country country = new Country();
      country.setId(new Long(criteria.getValue()));
      Predicate countryPredicate = builder.equal(subRoot.get("country"), country);

      subQuery.select(subRoot).where(regionPredicate, countryPredicate);
      return builder.exists(subQuery);
    }
    else if (criteria.getKey().equalsIgnoreCase("regionId")) {
      Region region = new Region();
      region.setId(new Long(criteria.getValue()));
      return builder.equal(root.get("region"), region);
    }
    else if (criteria.getKey().equalsIgnoreCase("yearFrom")) {
      return builder.greaterThanOrEqualTo(root.get("year"), new Integer(criteria.getValue()));
    }
    else if (criteria.getKey().equalsIgnoreCase("yearTo")) {
      return builder.lessThanOrEqualTo(root.get("year"), new Integer(criteria.getValue()));
    }
    else {
      return null;
    }
  }
}