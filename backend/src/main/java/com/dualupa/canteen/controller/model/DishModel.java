package com.dualupa.canteen.controller.model;

import com.dualupa.canteen.dao.dish.Category;
import com.dualupa.canteen.dao.dish.Dish;
import com.dualupa.canteen.dao.dish.Weight;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author avbelyaev
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DishModel {

    private final String id;

    private final String name;

    private final BigDecimal price;

    private final NutritionModel nutrition;

    private final List<WeightModel> weights;

    private final Collection<String> categories;

    private final Collection<CanteenModel> availableAt;

    private final String imageUrl;

    public DishModel(@Nonnull Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.nutrition = new NutritionModel(dish.getNutrition());
        this.weights = dish.getWeights().stream()
                .map(WeightModel::new)
                .collect(Collectors.toList());
        this.categories = dish.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        this.availableAt = dish.getAvailableAt().stream()
                .map(CanteenModel::new)
                .collect(Collectors.toList());
        this.imageUrl = dish.getImageUrl();
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class WeightModel {
        private final float weight;

        private final String unit;

        WeightModel(Weight weight) {
            this.weight = weight.getWeight();
            this.unit = weight.getUnit().getName();
        }
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class NutritionModel {

        private final float calories;         // калории

        private final float fats;             // жиры

        private final float proteins;         // белки

        private final float carbohydrates;    // углеводы

        NutritionModel(Dish.Nutrition nutrition) {
            this.calories = nutrition.getCalories();
            this.fats = nutrition.getFats();
            this.proteins = nutrition.getProteins();
            this.carbohydrates = nutrition.getCarbohydrates();
        }
    }
}