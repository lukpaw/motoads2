package com.blogspot.coderlife.motoads2.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Advert {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "model_id")
  private Model model;
  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;
  private int year;
  private int price;
  private String imageUrl;
}