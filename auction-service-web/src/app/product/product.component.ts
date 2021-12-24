import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../product/Product';
import { Bids } from '../product/Bids';
import { ProductBids } from '../product/ProductBids';
import { ProductBidsDetailsService } from '../product-service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  productIdToSearch: string;
  productBids: ProductBids;

  constructor(private route: ActivatedRoute,private router: Router,
       private productBidsDetailsService: ProductBidsDetailsService) { }

    ngOnInit() {
   
    // this.product = new Product();

    // this.productIdToSearch = this.route.snapshot.params['productId'];
    
    // this.productBidsDetailsService.getProductBids(this.productIdToSearch)
    //   .subscribe(data => {
    //     console.log(data)
    //     this.product = data;
    //   }, error => console.log(error));
  }

  getProductDetails(){

    //this.productId = this.route.snapshot.params['productId'];
    this.productBids = new ProductBids();
    this.productBidsDetailsService.getProductBids(this.productIdToSearch)
      .subscribe(data => {
        console.log(data)
        this.productBids = data;
      }, error => console.log(error));
  }
 
  isBidsNotEmptyObject() {
    return (this.productBids != null && this.productBids.bids.length !=0);
  }

  isProductNotEmptyObject() {
    return (this.productBids != null && this.productBids.product != null);
  }

}