package com.dualupa.canteen.controller;

import com.dualupa.canteen.dao.dish.Dish;
import com.dualupa.canteen.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author avbelyaev
 */
@RestController
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private CanteenService canteenService;

    @GetMapping()
    public ResponseEntity<Object> getAllDishes() {
        Collection<Dish> allDishes = this.canteenService.getAllDishes();
        return ResponseEntity.ok(allDishes);
    }
}
