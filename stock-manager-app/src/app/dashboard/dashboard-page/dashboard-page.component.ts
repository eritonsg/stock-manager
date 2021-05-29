import { Component, OnInit } from '@angular/core';
import Stock from 'src/app/components/model/stock-model';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.css']
})
export class DashboardPageComponent implements OnInit {

  stocks: Stock[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.fetchStocks();
  }

  async fetchStocks(): Promise<void> {
    this.stocks = await (await this.dashboardService.getStocks()).sort((a,b) => this.sortByDate(a,b));
  }

  sortByDate(a:Stock, b:Stock) {

    if (a.date > b.date) {
      return 1;
    }
    if (a.date < b.date) {
      return -1;
    }
    return 0;
  }

}
