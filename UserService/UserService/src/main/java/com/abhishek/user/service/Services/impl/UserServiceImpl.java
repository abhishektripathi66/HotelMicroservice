package com.abhishek.user.service.Services.impl;

import com.abhishek.user.service.Services.UserService;
import com.abhishek.user.service.entities.Hotel;
import com.abhishek.user.service.entities.Rating;
import com.abhishek.user.service.entities.User;
import com.abhishek.user.service.exceptions.ResourceNotFoundException;
import com.abhishek.user.service.external.services.HotelService;
import com.abhishek.user.service.repositories.UserRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate resttemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserID(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for(User user:users){
            user.setRatings(getRatingList(user.getUserID()));
        }
        return users;
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(" user with the given id :"+userId+" not found on the server !!"));
        //fetch rating of the above user from rating service
        //http://localhost:8083/ratings/users/
        List<Rating> forObject = getRatingListByFiegnClient(userId);
        user.setRatings(forObject);
        logger.info("{}",forObject);
        return user;
    }

    public List<Rating> getRatingList(String userId){
        Rating[] forObject = resttemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
        logger.info("{}",forObject);
        List<Rating> ratings = Arrays.stream(forObject).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity = resttemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {} ",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        return ratings;
    }

    public List<Rating> getRatingListByFiegnClient(String userId){
        Rating[] forObject = resttemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
        logger.info("{}",forObject);
        List<Rating> ratings = Arrays.stream(forObject).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = resttemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code: {} ",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        return ratings;
    }

    //http://localhost:8082/hotels/f4a410ac-5fd1-4b47-a1a1-78520ad016f9

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(User user) {
            userRepository.save(user);
    }
}
