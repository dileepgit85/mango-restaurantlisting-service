package com.mango.codedecode.restaurantlistingms.mapper;

import com.mango.codedecode.restaurantlistingms.dto.RestaurantDTO;
import com.mango.codedecode.restaurantlistingms.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
    Restaurant mapRestaurantDTORestaurant(RestaurantDTO restaurantDTO);
}
