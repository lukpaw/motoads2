import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/primeng';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  items: MenuItem[];

  ngOnInit() {
    this.items = [
      {label: 'Adverts', icon: 'plus', routerLink: ['/adverts']},
      {label: 'Add advert', icon: 'list', routerLink: ['/addAdvert']}
    ];
  }
}
