import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Stock from '../components/model/stock-model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  readonly baseUrl = `${environment.apiURL}`;

  constructor(private http: HttpClient) { }

  async getStocks(): Promise<Stock[]> {
    return await this.http.get<Stock[]>(`${this.baseUrl}/stocks`).toPromise();
  }

}
