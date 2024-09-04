package com.ecom.service.impl;

import com.ecom.exception.ProductPurchaseException;
import com.ecom.mapper.ModelMapper;
import com.ecom.model.Product;
import com.ecom.record.ProductPurchaseRequest;
import com.ecom.record.ProductPurchaseResponse;
import com.ecom.record.ProductRequest;
import com.ecom.record.ProductResponse;
import com.ecom.repository.ProductRepo;
import com.ecom.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Product product=mapper.toProduct(productRequest);

        return productRepo.save(product).getId();
    }

    @Override
    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        List<Integer> productId=request.stream().
                map(ProductPurchaseRequest::id).collect(Collectors.toList());
       List<Product>existProduct= productRepo.findAllByIdInOrderById(productId);
       if(productId.size()!=existProduct.size()){
           throw new ProductPurchaseException("one or more product is not available");
       }
       List<ProductPurchaseRequest> sortedRequest=request.stream()
               .sorted((i1,i2)->i1.id().compareTo(i2.id()))
               .collect(Collectors.toList());
        ArrayList<ProductPurchaseResponse> purchaseResponses=new ArrayList<ProductPurchaseResponse>();
       for(int i=0;i<sortedRequest.size();i++){
          Product product = existProduct.get(i);
          ProductPurchaseRequest purchaseRequest=sortedRequest.get(i);
          if(product.getAvailableQuantity()<purchaseRequest.quantity()){
              throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " +purchaseRequest.id());
          }
          product.setAvailableQuantity(product.getAvailableQuantity()-purchaseRequest.quantity());
          productRepo.save(product);
          purchaseResponses.add(mapper.toProductPurchaseResponse(product,purchaseRequest.quantity()));
       }
        return purchaseResponses;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepo.findById(productId).map(mapper::fromProduct)
                .orElseThrow(()-> new EntityNotFoundException("Product is not Available with id:"+productId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepo.findAll().stream().map(mapper::fromProduct).collect(Collectors.toList());
    }
}
