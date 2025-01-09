package org.gaenkov;

public class ApiRequestFactory {
    public static ApiRequest createRequest(String type, String productId) {
        if (type.equalsIgnoreCase("product")) {
            return new ProductRequest(productId);
        }
        // Можно добавить другие типы запросов
        throw new IllegalArgumentException("Unknown request type");
    }
}
