package org.gaenkov;

public class ApiClient {
    private static ApiClient instance;
    private String apiUrl = "https://api.wildberries.ru";

    private ApiClient() {}

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}

