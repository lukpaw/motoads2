import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {AppService} from './app.service';
import {AdvertsComponent} from './adverts/adverts.component';
import {AddAdvertComponent} from './add-advert/add-advert.component';
import {EditAdvertComponent} from './edit-advert/edit-advert.component';
import {AppRoutingModule} from './app-routing.module';
import {ButtonModule, DataListModule, DropdownModule, InputMaskModule, MessageModule, PanelMenuModule, TabMenuModule} from 'primeng/primeng';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    AdvertsComponent,
    AddAdvertComponent,
    EditAdvertComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    PanelMenuModule,
    TabMenuModule,
    DropdownModule,
    InputMaskModule,
    DataListModule,
    MessageModule,
    ButtonModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
