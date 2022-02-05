package com.paypal.bfs.test.bookingserv.exception;

import lombok.Data;

@Data
public class Error {
    String message;
    public Error(String message){
        this.message=message;
    }
}
