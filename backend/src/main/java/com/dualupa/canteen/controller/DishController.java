package com.dualupa.canteen.controller;

import com.dualupa.canteen.dao.Dish;
import com.dualupa.canteen.service.DishService;
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
    private DishService dishService;

    @GetMapping()
    public ResponseEntity<Object> getAllDishes() {
        Collection<Dish> allDishes = this.dishService.getAllDishes();
        return ResponseEntity.ok(allDishes);
    }
}
