import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/primeng";
import {AppService} from "../app.service";
import {Advert} from "../shared/advert";

@Component({
  selector: 'app-add-advert',
  templateUrl: './add-advert.component.html',
  styleUrls: ['./add-advert.component.css']
})
export class AddAdvertComponent implements OnInit {
  brandItems: SelectItem[] = [];
  modelItems: SelectItem[] = [];
  countryItems: SelectItem[] = [];
  regionItems: SelectItem[] = [];
  advert: Advert = new Advert();
  advertAddedRes: String = null;

  constructor(private appService: AppService) {
  }

  ngOnInit() {
    this.initDefaultBrandItems();
    this.appService.getBrands().subscribe(brands => {
        for (const brand of brands) {
          this.brandItems.push({label: brand.name, value: brand.id});
        }
      }
    );

    this.initDefaultModelItems();

    this.initDefaultCountryItems();
    this.appService.getCountries().subscribe(
      countries => {

        for (const country of countries) {
          this.countryItems.push({label: country.name, value: country.id});
        }
      }
    );

    this.initDefaultRegionItems();
  }

  initDefaultBrandItems() {
    this.advert.brandId = null;
    this.brandItems = [];
    this.brandItems.push({label: 'Select Brand', value: null});
  }

  initDefaultModelItems() {
    this.advert.modelId = null;
    this.modelItems = [];
    this.modelItems.push({label: 'Select Model', value: null});
  }

  initDefaultCountryItems() {
    this.advert.countryId = null;
    this.countryItems = [];
    this.countryItems.push({label: 'Select Country', value: null});
  }

  initDefaultRegionItems() {
    this.advert.regionId = null;
    this.regionItems = [];
    this.regionItems.push({label: 'Select Region', value: null});
  }

  onChangeBrand() {
    this.initDefaultModelItems();
    if (this.advert.brandId != null) {
      this.appService.getModels(this.advert.brandId).subscribe(
        models => {
          for (const model of models) {
            this.modelItems.push({label: model.name, value: model.id});
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  onChangeCountry() {
    this.initDefaultRegionItems();
    if (this.advert.countryId != null) {
      this.appService.getRegions(this.advert.countryId).subscribe(
        regions => {
          for (const region of regions) {
            this.regionItems.push({label: region.name, value: region.id});
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  save() {
    console.log(JSON.stringify(this.advert));
    this.appService.addAdvert(this.advert).subscribe(advert => console.log("Added"));
    this.advertAddedRes = "Advert added successfully";
    this.advert = new Advert();
  }

}
