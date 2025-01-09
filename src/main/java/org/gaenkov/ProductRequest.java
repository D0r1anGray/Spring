package org.gaenkov;

public class ProductRequest implements ApiRequest {
    private String productId;

    public ProductRequest(String productId) {
        this.productId = productId;
    }

    @Override
    public String execute() {
        return "Product info for ID: " + productId;
    }
}
