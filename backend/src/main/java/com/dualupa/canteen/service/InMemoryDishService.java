package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.Canteen;
import com.dualupa.canteen.dao.Dish;
import com.dualupa.canteen.dao.Weight;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.dualupa.canteen.dao.Category.SOUP;
import static com.dualupa.canteen.dao.Weight.Unit.GRAM;

/**
 * @author avbelyaev
 */
@Getter
@Service
@Slf4j
public class InMemoryDishService implements DishService {

    private Collection<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void fillInitialCatalogData() {
        log.info("Filling initial catalog");

        Canteen iuCanteen = new Canteen("Столовая ИУ", "ГЗ, 3 этаж");
        Canteen mainGZCanteen = new Canteen("ГЗ Главная", "ГЗ, слева");
        Canteen ulkCanteen = new Canteen("УЛК Главаня", "УЛК, 2 этаж");

        Dish soup1 = Dish.builder()
                .name("Суп гороховый с картофелем и копченостями")
                .price(BigDecimal.valueOf(50))
                .weights(Arrays.asList(
                        new Weight(25, GRAM),
                        new Weight(250, GRAM)))
                .categories(Collections.singletonList(SOUP))
                .nutrition(Dish.Nutrition.builder()
                        .calories(250)
                        .carbohydrates(33)
                        .fats(60.5f)
                        .proteins(0.5f)
                        .build())
                .availableAt(Arrays.asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .build();

        this.dishes.add(soup1);

        log.info("Catalog has been filled with {} dishes", this.dishes.size());
    }

    @Nonnull
    @Override
    public Collection<Dish> getAllDishes() {
        return this.dishes;
    }
}
