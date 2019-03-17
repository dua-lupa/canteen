package com.dualupa.canteen.dao.dish;

import com.dualupa.canteen.dao.canteen.Canteen;
import lombok.Builder;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author avbelyaev
 */
// TODO add dish picture
@Getter
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

    // энергетическая ценность
    @Builder
    @Getter
    public static class Nutrition {

        private float calories;         // калории

        private float fats;             // жиры

        private float proteins;         // белки

        private float carbohydrates;    // углеводы
    }
}


