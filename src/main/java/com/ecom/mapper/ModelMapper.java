package com.ecom.mapper;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.record.ProductPurchaseResponse;
import com.ecom.record.ProductRequest;
import com.ecom.record.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder().id(request.id())
                        .name(request.name())
                                .description(request.description())
                                        .price(request.price())
                                                .availableQuantity(request.availableQuantity())
                                                        .category(Category.builder().id(request.id()).build())
                .build();
    }
    public ProductResponse fromProduct(Product product){
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }
public ProductPurchaseResponse toProductPurchaseResponse(Product product,double availableQuantity){
    return    new ProductPurchaseResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                availableQuantity
        );
}


}
