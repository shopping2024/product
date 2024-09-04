package com.ecom.controller;

import com.ecom.record.ProductPurchaseRequest;
import com.ecom.record.ProductPurchaseResponse;
import com.ecom.record.ProductRequest;
import com.ecom.record.ProductResponse;
import com.ecom.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest){
return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.OK);
    }
    
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid
                                                                         List<ProductPurchaseRequest> request
                                                                         ){
        return new ResponseEntity<>(productService.purchaseProduct(request),HttpStatus.OK);

    }
    
    @GetMapping("/{productId}")
public ResponseEntity<ProductResponse> findById(@PathVariable Integer productId){
      return new ResponseEntity<>(productService.findById(productId),HttpStatus.OK);
}
    
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
}



}
