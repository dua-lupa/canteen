package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.Canteen;
import com.dualupa.canteen.dao.Category;
import com.dualupa.canteen.dao.Dish;
import com.dualupa.canteen.dao.Weight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dualupa.canteen.dao.Category.DRINK;
import static com.dualupa.canteen.dao.Category.SOUP;
import static com.dualupa.canteen.dao.Weight.Unit.GRAM;
import static com.dualupa.canteen.dao.Weight.Unit.MILLILITER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author avbelyaev
 */
@Service
@Slf4j
public class InMemoryCanteenService implements CanteenService {

    private Collection<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void fillInitialCatalogData() {
        log.info("Filling initial catalog");

        Canteen iuCanteen = new Canteen("Столовая ИУ", "ГЗ, 3 этаж");
        Canteen mainGZCanteen = new Canteen("ГЗ Главная", "ГЗ, слева");
        Canteen ulkCanteen = new Canteen("УЛК Главаня", "УЛК, 2 этаж");

        Dish soup1 = Dish.builder()
                .name("Суп гороховый с картофелем и конечностями")
                .price(BigDecimal.valueOf(50))
                .weights(asList(
                        new Weight(25, GRAM),
                        new Weight(250, GRAM)))
                .categories(singletonList(SOUP))
                .nutrition(Dish.Nutrition.builder()
                        .calories(250)
                        .carbohydrates(33)
                        .fats(60.5f)
                        .proteins(0.5f)
                        .build())
                .availableAt(asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .build();

        Dish teaWithSugar = Dish.builder()
                .name("Чай с сахаром")
                .price(BigDecimal.valueOf(15))
                .weights(asList(
                        new Weight(200, MILLILITER),
                        new Weight(16, GRAM)
                ))
                .categories(singletonList(DRINK))
                .availableAt(asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .build();

        Dish makarony = Dish.builder()
                .name("Макароны отварные с маслом")
                .price(BigDecimal.valueOf(15))
                .weights(Collections.singletonList(new Weight(150, GRAM)))
                .categories(singletonList(Category.PORRIDGE))
                .nutrition(Dish.Nutrition.builder()
                        .calories(150)
                        .calories(15)
                        .fats(50)
                        .proteins(0)
                        .build())
                .availableAt(asList(iuCanteen, mainGZCanteen))
                .build();

        this.dishes.add(soup1);
        this.dishes.add(teaWithSugar);
        this.dishes.add(makarony);

        log.info("Catalog has been filled with {} dishes", this.dishes.size());
    }

    @Nonnull
    @Override
    public Collection<Dish> getAllDishes() {
        return this.dishes;
    }
}
