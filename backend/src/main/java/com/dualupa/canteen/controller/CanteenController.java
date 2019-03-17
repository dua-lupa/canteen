package com.dualupa.canteen.controller;

import com.dualupa.canteen.controller.model.CanteenModel;
import com.dualupa.canteen.controller.model.DishModel;
import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author avbelyaev
 */
@RestController
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @GetMapping("/api/canteens")
    public ResponseEntity getAllCanteens() {
        Collection<CanteenModel> allCanteens = this.canteenService
                .getAllCanteens().stream()
                .map(CanteenModel::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allCanteens);
    }

    @GetMapping("/api/canteens/{id}")
    public ResponseEntity getSingleCanteen(@PathVariable String id) {
        Optional<Canteen> canteen = this.canteenService.getCanteenById(id);
        return canteen.isPresent()
                ? ResponseEntity.ok(new CanteenModel(canteen.get()))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/canteens/{id}/dishes")
    public ResponseEntity getDishesForCanteen(@PathVariable String id) {
        Collection<DishModel> canteenDishes = this.canteenService
                .getDishesForCanteen(id).stream()
                .map(DishModel::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(canteenDishes);
    }
}
