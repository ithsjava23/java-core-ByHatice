package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public  class ProductRecord {
    private final UUID id;
    private final String productName;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID id, String productName, Category category, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }
    public UUID getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public UUID uuid() {
        return id;
    }
    public Category category() {
        return category;
    }
    public BigDecimal price() {
        return price;
    }
}