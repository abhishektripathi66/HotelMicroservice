package com.abhishek.user.service.external.services;

import com.abhishek.user.service.entities.Hotel;
import com.abhishek.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;
@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingService {

    @GetMapping()
    List<Rating> getRating();

    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);
}
