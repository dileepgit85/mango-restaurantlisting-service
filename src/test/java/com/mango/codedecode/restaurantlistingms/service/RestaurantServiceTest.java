package com.mango.codedecode.restaurantlistingms.service;

import com.mango.codedecode.restaurantlistingms.dto.RestaurantDTO;
import com.mango.codedecode.restaurantlistingms.entity.Restaurant;
import com.mango.codedecode.restaurantlistingms.mapper.RestaurantMapper;
import com.mango.codedecode.restaurantlistingms.repo.RestaurantRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepo restaurantRepo;

    @InjectMocks
    private RestaurantService restaurantService;

    public RestaurantServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        when(restaurantRepo.findAll()).thenReturn(Arrays.asList(restaurant1, restaurant2));

        List<RestaurantDTO> result = restaurantService.findAllRestaurants();

        assertEquals(2, result.size());
        verify(restaurantRepo, times(1)).findAll();
    }

    @Test
    void addRestaurantToDB() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        Restaurant restaurant = new Restaurant();
        when(restaurantRepo.save(any(Restaurant.class))).thenReturn(restaurant);

        RestaurantDTO result = restaurantService.addRestaurantToDB(restaurantDTO);

        assertNotNull(result);
        verify(restaurantRepo, times(1)).save(any(Restaurant.class));
    }

    @Test
    void findRestaurantById() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepo.findById(anyInt())).thenReturn(Optional.of(restaurant));

        ResponseEntity<RestaurantDTO> response = restaurantService.findRestaurantById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(restaurantRepo, times(1)).findById(1);
    }

    @Test
    void findRestaurantById_NotFound() {
        when(restaurantRepo.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<RestaurantDTO> response = restaurantService.findRestaurantById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(restaurantRepo, times(1)).findById(1);
    }
}