package com.abhishek.hotel.service.HotelService.services.impl;

import com.abhishek.hotel.service.HotelService.entities.Hotel;
import com.abhishek.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.abhishek.hotel.service.HotelService.repositories.HotelRepository;
import com.abhishek.hotel.service.HotelService.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    private Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Override
    public Hotel create(Hotel hotel) {
        String uid = UUID.randomUUID().toString();
        hotel.setId(uid);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with the given id  not found"));
    }
}
