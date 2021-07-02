import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { delay } from 'rxjs/operators';
import { HttpRequestLoadStatusService } from '../http-request-load-status.service';

@Component({
  selector: 'app-http-request-load-status',
  templateUrl: './http-request-load-status.component.html',
  styleUrls: ['./http-request-load-status.component.css']
})
export class HttpRequestLoadStatusComponent implements OnInit {

  constructor(
    private  httpRequestLoadStatusService: HttpRequestLoadStatusService,
    private spinnerService: NgxSpinnerService
  ) {}

  ngOnInit() {
    this.listenToLoading();
  }

  listenToLoading(): void {
    this.httpRequestLoadStatusService.loadingSub
      .pipe(delay(0))
      .subscribe((loading) => {
        if (loading == true) {
         this.spinnerService.show('dashboard-http-request-load-status');
        } else {
          this.spinnerService.hide('dashboard-http-request-load-status');
        }
      });
  }

}
