package com.dualupa.canteen.dao.dish;

import com.dualupa.canteen.dao.canteen.Canteen;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author avbelyaev
 */
// TODO add dish picture
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for mongo
@Data
@Document(collection = "Dishes")
public class Dish {

    public static final String PATH_TO_DISH_IMAGES = "/dish-images/";

    private static long count = 0;

    @Id
    private String id;

    private String name;

    private BigDecimal price;

    private Nutrition nutrition;

    private List<Weight> weights;

    private Collection<Category> categories;

    private Collection<Canteen> availableAt;

    private String imageUrl;

    @Builder
    public Dish(String name,
                BigDecimal price,
                Nutrition nutrition,
                List<Weight> weights,
                Collection<Category> categories,
                Collection<Canteen> availableAt,
                String imageUrl) {
        this.name = name;
        this.price = price;
        this.nutrition = nutrition;
        this.weights = weights;
        this.categories = categories;
        this.availableAt = availableAt;
        this.imageUrl = PATH_TO_DISH_IMAGES + imageUrl;
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


