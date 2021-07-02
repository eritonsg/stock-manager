import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';

import { HttpRequestLoadStatusService } from '../../components/http-request-load-status/http-request-load-status.service';


/**
 * This class intecepts http requests. When a request starts, we set the loadingSub property
 * in the HttpRequestLoadStatusService to true. After it completes, set the value to false.
 * If an error occurs while servicing the request, set the loadingSub property to false.
 * @class {HttpRequestInterceptor}
 */
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  constructor(
    private  httpRequestLoadStatusService: HttpRequestLoadStatusService,
  ) { }

  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {  
        
    const subscription = next.handle(req)
        .subscribe(
          success => {
            this.httpRequestLoadStatusService.setLoading(true, req.url);
          },
          error => {
            if (error.status == 0) {
              this.httpRequestLoadStatusService.setLoading(false, req.url);
            }
          },
          () => {
            this.httpRequestLoadStatusService.setLoading(false, req.url);
          });
    
    return next.handle(req);

  }

}