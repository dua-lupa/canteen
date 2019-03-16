package com.dualupa.canteen.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author avbelyaev
 */
// TODO add dish picture
@AllArgsConstructor
@Data
public class Dish {

    private String name;

    private BigDecimal price;

    private Nutrition nutrition;

    private List<Weight> weights;

    private Collection<DishCategory> categories;

    private Collection<Canteen> availableAt;

    // энергетическая ценность
    @AllArgsConstructor
    @Data
    private static class Nutrition {

        private float calories;         // калории

        private float fats;             // жиры

        private float proteins;         // белки

        private float carbohydrates;    // углеводы
    }

    private static class Weight {
        private float weight;

        private Unit unit;

        private enum Unit {
            GRAM("gram"),
            MILLILITER("ml");

            private final String unit;

            Unit(@Nonnull String unit) {
                this.unit = unit;
            }
        }
    }
}
