package com.dualupa.canteen.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author avbelyaev
 */
@RestController
@RequestMapping("/api")
public class CatalogController {

    @GetMapping("/catalog")
    public ResponseEntity<Object> getCardTypes() {
        return ResponseEntity.ok(new Catalog(Arrays.asList("Гречка", "Рис", "Компотик")));
    }

    // это даже не заглушки, а прост проверка что boot работает. струтктуры api еще нет :)
    @AllArgsConstructor
    @Data
    private static class Catalog {

        private List<String> catalog;
    }
}
