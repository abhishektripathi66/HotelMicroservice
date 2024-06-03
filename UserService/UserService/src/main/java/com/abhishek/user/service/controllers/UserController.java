package com.abhishek.user.service.controllers;

import com.abhishek.user.service.Services.UserService;
import com.abhishek.user.service.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
            User user1 = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get
    int retryCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="RatingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
//    @Retry(name="ratingHotelRetry",fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallBack")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("retryCount: {}",retryCount++);
        User user1 = userService.getUser(userId);
        return  ResponseEntity.ok(user1);
    }


    //creating fallback merthod for circuit breakers
    public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){

        logger.info("Fallback method is executed because service is down: ",ex.getMessage());
        User dummy = User.builder().email("dummy@gmail.com").name("dummy").about("The user is created as some service is down").build();
        return new ResponseEntity<>(dummy,HttpStatus.BAD_REQUEST);
    }

    //all users get

    @GetMapping
    public  ResponseEntity<List<User>> getAllUser(){
        List<User> alluser = userService.getAllUsers();
        return  ResponseEntity.ok(alluser);
    }

}
