import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';

import { catchError, retry } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import Stock from '../components/model/stock-model';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  readonly baseUrl = `${environment.apiURL}`;
  errorResponse!: HttpErrorResponse;

  constructor(private http: HttpClient) { }

  async getStocks(): Promise<Stock[]> {
    return await this.http.get<Stock[]>(`${this.baseUrl}/stocks`).toPromise();
  }

  findAllStocks(): Observable<HttpResponse<Stock[]>> {
    return this.http.get<Stock[]>(`${this.baseUrl}/stocks`, { observe: 'response' })
        .pipe(
          retry(3),
          catchError(this.handleError)
        );
  }

  private handleError(error: HttpErrorResponse) {

    if (error.status === 0) {
      
      this.errorResponse = {
        "headers": error.headers,
        "name": "HttpErrorResponse",
        "status": error.status,
        "statusText": error.statusText,
        "error": error.error,
        "message": error.error.message ? error.error.message : `An error has occurred while sending request to ${error.url}.`,
        "url": error.url!,
        "ok": error.ok,
        "type": error.type
      }

      console.error('Anything wrong happened; see error message below.');

    } else {

      this.errorResponse = {
        "headers": error.headers,
        "name": "HttpErrorResponse",
        "status": error.status,
        "statusText": error.statusText,
        "error": error.error,
        "message": error.error.message ? error.error.message : `An error has occurred while sending request to ${error.url}.`,
        "url": error.url!,
        "ok": error.ok,
        "type": error.type
      }

      console.error('Anything wrong happened; see error message below.');

    }

      return throwError(this.errorResponse);

    }

}
