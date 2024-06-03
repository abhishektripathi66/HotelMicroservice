package com.abhishek.rating.service.RatingService.controllers;

import com.abhishek.rating.service.RatingService.entities.Rating;
import com.abhishek.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    //create rating

    @PostMapping()
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.creatRating(rating));
    }

    //get all
    @GetMapping()
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUser(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotel(hotelId));
    }


}
