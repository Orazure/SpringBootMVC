package com.rioc.ws.exceptions;

public class ExceptionMessage {
    private final String message;

    public ExceptionMessage(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
