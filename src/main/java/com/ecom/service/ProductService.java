package com.ecom.service;

import com.ecom.record.ProductPurchaseRequest;
import com.ecom.record.ProductPurchaseResponse;
import com.ecom.record.ProductRequest;
import com.ecom.record.ProductResponse;

import java.util.List;

public interface ProductService {
    public Integer createProduct(ProductRequest productRequest);

  public   List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request);

  public   ProductResponse findById(Integer productId);

   public List<ProductResponse> findAll();
}
