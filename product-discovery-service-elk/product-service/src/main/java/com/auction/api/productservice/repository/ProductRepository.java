package com.auction.api.productservice.repository;

import com.auction.api.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
