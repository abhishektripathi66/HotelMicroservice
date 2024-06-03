package com.abhishek.rating.service.RatingService.services;

import com.abhishek.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

        //create
    Rating creatRating(Rating rating);
    //get all rating
    List<Rating> getRatings();
    //user wise all rating
    List<Rating> getRatingByUser(String userId);
    //hotel wise all rating
    List<Rating> getRatingByHotel(String hotelId);



}
