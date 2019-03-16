package com.dualupa.canteen.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author avbelyaev
 */
// TODO add dish picture
@Data
public class Dish {

    private static long count = 0;

    private String id;

    private String name;

    private BigDecimal price;

    private Nutrition nutrition;

    private List<Weight> weights;

    private Collection<Category> categories;

    private Collection<Canteen> availableAt;

    @Builder
    public Dish(String name,
                BigDecimal price,
                Nutrition nutrition,
                List<Weight> weights,
                Collection<Category> categories,
                Collection<Canteen> availableAt) {
        this.name = name;
        this.price = price;
        this.nutrition = nutrition;
        this.weights = weights;
        this.categories = categories;
        this.availableAt = availableAt;
        this.id = String.valueOf(Dish.count++);
    }

    public boolean isAvailableAtCanteen(@Nonnull String canteenId) {
        return this.availableAt.stream()
                .anyMatch(canteen -> canteen.getId().equalsIgnoreCase(canteenId));
    }

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


