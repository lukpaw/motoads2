import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdvertsComponent} from './adverts/adverts.component';
import {EditAdvertComponent} from './edit-advert/edit-advert.component';
import {AddAdvertComponent} from './add-advert/add-advert.component';

const routes: Routes = [
  {path: '', redirectTo: '/adverts', pathMatch: 'full'},
  {path: 'adverts', component: AdvertsComponent},
  {path: 'addAdvert', component: AddAdvertComponent},
  {path: 'editAdvert/:advertId', component: EditAdvertComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
