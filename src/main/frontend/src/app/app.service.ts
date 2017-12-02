import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Advert} from './shared/advert';
import {Brand} from './shared/brand';
import {Country} from './shared/country';
import {Region} from './shared/region';
import {environment} from "../environments/environment";
import {Model} from "./shared/model";
import 'rxjs/add/operator/map';
import "rxjs/add/operator/catch";

@Injectable()
export class AppService {

  constructor(private http: Http) {
  }

  getBrands(): Observable<Brand[]> {
    return this.http.get(environment.apiUrl + '/api/brand/').map(res => res.json());
  }

  // TODO brandId in url in the such way is not the best solution
  getModels(brandId: number): Observable<Model[]> {
    return this.http.get(environment.apiUrl + '/api/model/' + brandId).map(res => res.json());
  }

  getCountries(): Observable<Country[]> {
    return this.http.get(environment.apiUrl + '/api/country/').map(res => res.json());
  }

  // TODO countryId in url in the such way is not the best solution
  getRegions(countryId: number): Observable<Region[]> {
    return this.http.get(environment.apiUrl +  '/api/region/' + countryId).map(res => res.json());
  }

  getAdverts(): Observable<Advert[]> {
    return this.http.get(environment.apiUrl + '/api/advert/').map(res => res.json());
  }

  getAdvert(advertId: number): Observable<Advert> {
    return this.http.get(environment.apiUrl + '/api/advert/' + advertId).map(res => res.json());
  }

  addAdvert(advert: Advert) : Observable<String> {
    // TODO Text response is maybe not enough
    return this.http.post(environment.apiUrl + '/api/advert/', advert)
      .map(res => res.text())
      .catch(err => err);
  }

  updateAdvert(advert: Advert) : Observable<String> {
    // TODO Text response is maybe not enough
    return this.http.put(environment.apiUrl + '/api/advert/', advert)
      .map(res => res.text())
      .catch(err => err);
  }

  getAdvertByAttrs(sortBy: string, modelId: number, countryId: number, regionId: number, yearFrom: number, yearTo: number): Observable<Advert[]> {
    let attrs = '';
    let nextAttr = false;

    if (sortBy != null) {
      attrs += 'sortBy:' + sortBy;
      nextAttr = true;
    }
    if (modelId != null) {
      if (nextAttr) {
        attrs += ',';
      }
      attrs += 'modelId:' + modelId;
      nextAttr = true;
    }
    if (countryId != null) {
      if (nextAttr) {
        attrs += ',';
      }
      attrs += 'countryId:' + countryId;
      nextAttr = true;
    }
    if (regionId != null) {
      if (nextAttr) {
        attrs += ',';
      }
      attrs += 'regionId:' + regionId;
      nextAttr = true;
    }
    if (yearFrom != null) {
      if (nextAttr) {
        attrs += ',';
      }
      attrs += 'yearFrom:' + yearFrom;
      nextAttr = true;
    }
    if (yearTo != null) {
      if (nextAttr) {
        attrs += ',';
      }
      attrs += 'yearTo:' + yearTo;
    }
    return this.http.get(environment.apiUrl + '/api/advertsearch?attrs=' + attrs).map(res => res.json());
  }

}
