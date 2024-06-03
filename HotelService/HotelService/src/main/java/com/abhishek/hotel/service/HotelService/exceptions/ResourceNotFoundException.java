package com.abhishek.hotel.service.HotelService.exceptions;

import org.springframework.stereotype.Service;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }

    public  ResourceNotFoundException(){
        super("Resource not found ! !");
    }
}
