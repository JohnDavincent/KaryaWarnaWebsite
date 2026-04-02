package com.example.transaction_services.projection;

import java.util.UUID;

public interface ProductStockProjection {
    UUID getProductId();
    Integer getQuantity();

}
