import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HttpRequestLoadStatusComponent } from './http-request-load-status/http-request-load-status/http-request-load-status.component';


@NgModule({ 
  declarations: [
    HeaderComponent,
    FooterComponent,
    HttpRequestLoadStatusComponent,
  ],
  imports: [
    CommonModule,
    NgxSpinnerModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    HttpRequestLoadStatusComponent,
  ],
  providers: 
  [
    NgxSpinnerService
  ]
})
export class ComponentsModule { }
