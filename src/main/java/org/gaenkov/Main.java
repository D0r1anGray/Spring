package org.gaenkov;

public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = ApiClient.getInstance();
        ApiRequest request = ApiRequestFactory.createRequest("product", "12345");
        String response = request.execute();

        ResponseHandlerContext context = new ResponseHandlerContext();
        context.setHandler(new ProductResponseHandler());
        context.handleResponse(response);
    }
}
