package com.kodem.demo.linkedindemo.authentication.payload;

// This Class is just to refactor the response body
public class UserAuthResponse {
    private String message;
    private String messageType;

    // Constructor
    public UserAuthResponse() {
    }

    public UserAuthResponse(String message, String messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    // Message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Message Type
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    // To String conversion
    @Override
    public String toString() {
        return "{\"message\": \"" + message + "\",\"messageType\": \"" + messageType + "\"}";
    }

}
