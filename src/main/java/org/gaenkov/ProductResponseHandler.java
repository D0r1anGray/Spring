package org.gaenkov;

public class ProductResponseHandler implements ResponseHandler {
    @Override
    public void handle(String response) {
        System.out.println("Handling product response: " + response);
    }
}