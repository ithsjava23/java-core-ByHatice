package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final String name;
    private final List<ProductRecord> products = new ArrayList<>();
    private final List <ProductRecord> updatedProduct = new ArrayList<>();
    private Warehouse(String name) {
        this.name = name;
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }
    public static Warehouse getInstance() {
        return new Warehouse("");
    }
    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }
    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(products);
    }
    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }
    public List<ProductRecord> getChangedProducts() {
        return new ArrayList<>(updatedProduct);
    }
    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::getCategory));
    }
    public Optional<ProductRecord> getProductById(UUID id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public ProductRecord addProduct(UUID uuid, String productName, Category category, BigDecimal price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Product name can't be null or empty.");
        if (category == null)
            throw new IllegalArgumentException("Category can't be null.");
        if (price == null)
            price = BigDecimal.valueOf(0);
        if(uuid == null)
            uuid = UUID.randomUUID();
        if (getProductById(uuid).isPresent())
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        ProductRecord productRecord = new ProductRecord(uuid, productName, category, price);
        products.add(productRecord);
        return productRecord;
    }
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Optional<ProductRecord> productToChange = getProductById(id);
        productToChange.ifPresent(product -> {
            product.setPrice(newPrice);
            updatedProduct.add(product);
        });
        if(productToChange.isEmpty())
            throw  new IllegalArgumentException("Product with that id doesn't exist.");
    }
}
