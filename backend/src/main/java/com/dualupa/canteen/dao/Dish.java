package com.dualupa.canteen.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author avbelyaev
 */
// TODO add dish picture
@Builder
@Data
public class Dish {

    private String name;

    private BigDecimal price;

    private Nutrition nutrition;

    private List<Weight> weights;

    private Collection<Category> categories;

    private Collection<Canteen> availableAt;

    @JsonProperty("categories")
    public Collection<String> categories() {
        return this.categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    // энергетическая ценность
    @Builder
    @Data
    public static class Nutrition {

        private float calories;         // калории

        private float fats;             // жиры

        private float proteins;         // белки

        private float carbohydrates;    // углеводы
    }
}


