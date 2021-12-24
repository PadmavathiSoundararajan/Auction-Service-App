import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductBidsDetailsService {

  private baseUrl = "https://hhe7cmi6gj.execute-api.us-east-2.amazonaws.com/Dev/e-auction/api/v1/seller/show-bids";

  constructor(private http: HttpClient) { }

  getProductBids(productId: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/${productId}`);
  }
}