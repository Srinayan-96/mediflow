package com.example.medicore.api;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // getters instead of manually writing, used lombok getter
//    public boolean isSuccess() { return success; }
//    public String getMessage() { return message; }
//    public T getData() { return data; }
//    public LocalDateTime getTimestamp() { return timestamp; }
}
