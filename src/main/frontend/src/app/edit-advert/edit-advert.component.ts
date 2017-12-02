import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/primeng";
import {AppService} from "../app.service";
import {Advert} from "../shared/advert";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';

@Component({
  selector: 'app-edit-advert',
  templateUrl: './edit-advert.component.html',
  styleUrls: ['./edit-advert.component.css']
})
export class EditAdvertComponent implements OnInit {
  brandItems: SelectItem[] = [];
  modelItems: SelectItem[] = [];
  countryItems: SelectItem[] = [];
  regionItems: SelectItem[] = [];
  advert: Advert = new Advert;
  tmpModelId: number;
  tmpRegionId: number;

  constructor(private appService: AppService, private route: ActivatedRoute, private location: Location) {
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

    this.getAdvert();
  }

  getAdvert(): void {
    const advertId = +this.route.snapshot.paramMap.get('advertId');
    this.appService.getAdvert(advertId).subscribe(advert => {
      this.advert = advert;
      this.tmpModelId = this.advert.modelId;
      this.tmpRegionId = this.advert.regionId;
      console.log(JSON.stringify(this.advert));
      this.onChangeBrand();
      this.onChangeCountry();
    });
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
          this.advert.modelId = this.tmpModelId;
          //console.log('changeBrand - ' + JSON.stringify(this.advert));

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
          this.advert.regionId = this.tmpRegionId;
          //console.log('changeCountry - ' + JSON.stringify(this.advert));
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  onSubmit() {
    console.log(JSON.stringify(this.advert));
    this.appService.updateAdvert(this.advert).subscribe(advert => {
      console.log("Updated");
      this.location.back();
    });
  }

}
