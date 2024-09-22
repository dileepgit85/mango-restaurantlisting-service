package com.mango.codedecode.restaurantlistingms.service;

import com.mango.codedecode.restaurantlistingms.dto.RestaurantDTO;
import com.mango.codedecode.restaurantlistingms.entity.Restaurant;
import com.mango.codedecode.restaurantlistingms.mapper.RestaurantMapper;
import com.mango.codedecode.restaurantlistingms.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDTOList;
    }

    public RestaurantDTO addRestaurantToDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTORestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
        Optional<Restaurant> restaurantOptional = restaurantRepo.findById(id);
        if(restaurantOptional.isPresent()) {
            return new ResponseEntity<> (RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurantOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
    }
}
