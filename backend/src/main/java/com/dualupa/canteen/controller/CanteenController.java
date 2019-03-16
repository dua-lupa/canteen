package com.dualupa.canteen.controller;

import com.dualupa.canteen.dao.Canteen;
import com.dualupa.canteen.dao.Dish;
import com.dualupa.canteen.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

/**
 * @author avbelyaev
 */
@RestController
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @GetMapping("/api/canteens")
    public ResponseEntity getAllCanteens() {
        Collection<Canteen> allCanteens = this.canteenService.getAllCanteens();
        return ResponseEntity.ok(allCanteens);
    }

    @GetMapping("/api/canteens/{id}")
    public ResponseEntity getSingleCanteen(@PathVariable String id) {
        Optional<Canteen> canteen = this.canteenService.getCanteenById(id);
        return canteen.isPresent()
                ? ResponseEntity.ok(canteen.get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/canteens/{id}/dishes")
    public ResponseEntity getDishesForCanteen(@PathVariable String id) {
        Collection<Dish> canteenDishes = this.canteenService.getDishesForCanteen(id);
        return ResponseEntity.ok(canteenDishes);
    }
}
