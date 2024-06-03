package com.abhishek.rating.service.RatingService;

import ch.qos.logback.core.CoreConstants;
import com.abhishek.rating.service.RatingService.entities.Rating;
import com.abhishek.rating.service.RatingService.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;
	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("Created using feign client").build();
		Rating rating1 = ratingService.creatRating(rating);
		System.out.println("new rating created");
	}

}
