package com.kodem.demo.linkedindemo.authentication.payload;

public class UserAuthResponse {
    private String message;
    private String messageType;

    public UserAuthResponse() {
    }

    public UserAuthResponse(String message, String messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "{\"message\": \"" + message + "\",\"messageType\": \"" + messageType + "\"}";
    }

}
