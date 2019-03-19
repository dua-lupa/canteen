package com.dualupa.canteen.controller.model;

import com.dualupa.canteen.dao.dish.Category;
import com.dualupa.canteen.dao.dish.Dish;
import com.dualupa.canteen.dao.dish.Dish.Nutrition;
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

    private final Nutrition nutrition;

    private final List<WeightModel> weights;

    private final Collection<String> categories;

    private final Collection<CanteenModel> availableAt;

    private final String imageUrl;

    public DishModel(@Nonnull Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.nutrition = dish.getNutrition();
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
    public class WeightModel {
        private final float weight;

        private final String unit;

        WeightModel(Weight weight) {
            this.weight = weight.getWeight();
            this.unit = weight.getUnit().getName();
        }
    }
}