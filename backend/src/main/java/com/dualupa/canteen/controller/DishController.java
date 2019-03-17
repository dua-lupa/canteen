package com.dualupa.canteen.controller;

import com.dualupa.canteen.controller.model.DishModel;
import com.dualupa.canteen.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        List<DishModel> allDishes = this.canteenService.getAllDishesSortedByPrice().stream()
                .map(DishModel::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allDishes);
    }
}
