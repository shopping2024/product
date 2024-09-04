package com.ecom.record;

import java.math.BigDecimal;

public record ProductResponse(Integer id,
        String name,
         String description,
         double availableQuantity,
         BigDecimal price,
                              Integer category_id,
                              String categoryName,
                              String categoryDescription ) {
}
