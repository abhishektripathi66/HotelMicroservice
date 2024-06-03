package com.abhishek.hotel.service.HotelService.repositories;

import com.abhishek.hotel.service.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {

}
