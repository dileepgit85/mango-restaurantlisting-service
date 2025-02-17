package com.mango.codedecode.restaurantlistingms.repo;

import com.mango.codedecode.restaurantlistingms.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
}
