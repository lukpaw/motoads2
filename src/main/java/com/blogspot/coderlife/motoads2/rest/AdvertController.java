package com.blogspot.coderlife.motoads2.rest;

import com.blogspot.coderlife.motoads2.domain.Advert;
import com.blogspot.coderlife.motoads2.service.search.AdvertSpecificationsBuilder;
import com.blogspot.coderlife.motoads2.service.AdvertService;
import com.blogspot.coderlife.motoads2.service.dto.AdvertDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class AdvertController {
  private final AdvertService advertService;

  @Autowired
  public AdvertController(AdvertService advertService) {
    this.advertService = advertService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/advert/")
  public Collection<AdvertDTO> getAdverts() {
    return advertService.getAdverts();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/advertsearch")
  @ResponseBody
  public List<AdvertDTO> search(@RequestParam(value = "attrs") String search) {
    AdvertSpecificationsBuilder builder = new AdvertSpecificationsBuilder();
    Pattern pattern = Pattern.compile("(\\w+?)(:)(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(3));
    }

    Specification<Advert> spec = builder.build();
    return advertService.getAdverts(spec);
  }
}