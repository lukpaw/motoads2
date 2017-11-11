import {Component, OnInit} from '@angular/core';
import {Advert} from '../shared/advert';
import {AppService} from '../app.service';
import {MenuItem, SelectItem} from 'primeng/primeng';
import {Model} from '../shared/model';
import {Country} from '../shared/country';
import {Region} from '../shared/region';
import {SortBy} from "../shared/sortby";

@Component({
  selector: 'app-adverts',
  templateUrl: './adverts.component.html',
  styleUrls: ['./adverts.component.css']
})
export class AdvertsComponent implements OnInit {
  adverts: Advert[] = [];
  brandItems: MenuItem[] = [];
  countryItems: SelectItem[] = [];
  regionItems: SelectItem[] = [];
  sortBy: SelectItem[] = [
    {label: 'Sort by', value: null},
    {label: 'Year', value: { id: 'year', name: 'Year'}},
    {label: 'Price', value: { id: 'price', name: 'Price'}}
  ];

  selectedModel: Model;
  selectedCountry: Country;
  selectedRegion: Region;
  selectedYearFrom: number;
  selectedYearTo: number;
  selectedSortBy: SortBy;

  constructor(private appService: AppService) {
  }

  ngOnInit() {
    this.appService.getBrands().subscribe(brands => {
        for (const brand of brands) {
          const modelItems = [];
          for (const model of brand.models) {
            modelItems.push({
              label: model.name, queryParams: {'id': model.id, 'name': brand.name + ' ' + model.name}, command: (event) => {
                // console.log("event.item.label = " + event.item.label + " " + event.item.queryParams.modelId);
                this.selectedModel = event.item.queryParams;
                this.searchAdverts();
              }
            });
          }
          this.brandItems.push({label: brand.name, items: modelItems});
        }
      }
    );

    this.initDefaultCountryItems();

    this.appService.getCountries().subscribe(
      countries => {

        for (const country of countries) {
          this.countryItems.push({label: country.name, value: {id: country.id, name: country.name}});
        }
      }
    );

    this.initDefaultRegionItems();

    this.appService.getAdverts().subscribe(adverts => this.adverts = adverts);
  }

  searchAdverts() {
    let selectedSortById = null;
    if (this.selectedSortBy != null) {
      selectedSortById = this.selectedSortBy['id'];
    }
    let selectedModelId = null;
    if (this.selectedModel != null) {
      selectedModelId = this.selectedModel['id'];
    }
    let selectedCountryId = null;
    if (this.selectedCountry != null) {
      selectedCountryId = this.selectedCountry['id'];
    }
    let selectedRegionId = null;
    if (this.selectedRegion != null) {
      selectedRegionId = this.selectedRegion['id'];
    }
    this.appService
      .getAdvertByAttrs(selectedSortById, selectedModelId, selectedCountryId, selectedRegionId, this.selectedYearFrom, this.selectedYearTo)
      .subscribe(adverts => this.adverts = adverts);
  }

  initDefaultCountryItems() {
    this.selectedCountry = null;
    this.countryItems = [];
    this.countryItems.push({label: 'Select Country', value: null});
  }

  initDefaultRegionItems() {
    this.selectedRegion = null;
    this.regionItems = [];
    this.regionItems.push({label: 'Select Region', value: null});
  }

  onclickClear() {
    this.selectedSortBy = null;
    this.selectedModel = null;
    this.initDefaultCountryItems();
    this.initDefaultRegionItems();
    this.selectedYearFrom = null;
    this.selectedYearTo = null;
    this.appService.getAdverts().subscribe(adverts => this.adverts = adverts);
  }

  onChangeCountry() {
    console.log('selectedCountryId=' + this.selectedCountry['id']);
    this.initDefaultRegionItems();
    this.appService.getRegions(this.selectedCountry['id']).subscribe(
      regions => {
        // console.log("regions=" + regions);
        for (const region of regions) {
          // console.log("region=" + region);
          this.regionItems.push({label: region.name, value: {id: region.id, name: region.name}});
        }
      },
      error => {
        console.log(error);
      }
    );
    this.searchAdverts();
  }

  onChangeRegion() {
    console.log('selectedRegionId=' + this.selectedRegion['id']);
    this.searchAdverts();
  }

  onChangeYearFrom() {
    console.log('selectedYearFrom=' + this.selectedYearFrom);
    this.searchAdverts();
  }

  onChangeYearTo() {
    console.log('selectedYearTo=' + this.selectedYearTo);
    this.searchAdverts();
  }

  onChangeSortBy() {
    console.log('selectedSortById=' + this.selectedSortBy['id']);
    this.searchAdverts();
  }

  anyFilter() {
    if (this.selectedModel || this.selectedCountry || this.selectedRegion || this.selectedYearFrom || this.selectedYearTo) {
      return true;
    }
    else {
      return false;
    }
  }

  anyFilterOrSort() {
    if (this.anyFilter() || this.selectedSortBy) {
      return true;
    }
    else {
      return false;
    }
  }

}
