import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Advert} from './shared/advert';
import {Brand} from './shared/brand';
import {Country} from './shared/country';
import {Region} from './shared/region';
import {environment} from "../environments/environment";
import {Model} from "./shared/model";

@Injectable()
export class AppService {

  constructor(private http: Http) {
  }

  getBrands(): Observable<Brand[]> {
    return this.http.get(environment.apiUrl + '/api/brand/').map(res => res.json());
  }

  // TODO brandId not best solution in url in such way
  getModels(brandId: number): Observable<Model[]> {
    return this.http.get(environment.apiUrl + '/api/model/' + brandId).map(res => res.json());
  }

  getCountries(): Observable<Country[]> {
    return this.http.get(environment.apiUrl + '/api/country/').map(res => res.json());
  }

  // TODO countryId not best solution in url in such way
  getRegions(countryId: number): Observable<Region[]> {
    return this.http.get(environment.apiUrl +  '/api/region/' + countryId).map(res => res.json());
  }

  getAdverts(): Observable<Advert[]> {
    return this.http.get(environment.apiUrl + '/api/advert/').map(res => res.json());
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
