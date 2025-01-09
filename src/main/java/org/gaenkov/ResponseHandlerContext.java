package org.gaenkov;

public class ResponseHandlerContext {
    private ResponseHandler handler;

    public void setHandler(ResponseHandler handler) {
        this.handler = handler;
    }

    public void handleResponse(String response) {
        handler.handle(response);
    }
}
