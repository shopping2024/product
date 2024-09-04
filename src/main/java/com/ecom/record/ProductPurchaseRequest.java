package com.ecom.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id is required")
        Integer id,
                                     @Positive(message = "quantity is mandatory")
                                     double quantity) {
}
