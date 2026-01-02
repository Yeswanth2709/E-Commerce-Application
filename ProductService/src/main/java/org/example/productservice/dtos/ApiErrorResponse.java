package org.example.productservice.dtos;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ApiErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timeStamp;

    public ApiErrorResponse(String message,int status){
        this.message=message;
        this.status=status;
        this.timeStamp=LocalDateTime.now();
    }


}
