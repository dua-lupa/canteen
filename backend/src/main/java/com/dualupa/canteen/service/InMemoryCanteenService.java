package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.canteen.Schedule;
import com.dualupa.canteen.dao.dish.Category;
import com.dualupa.canteen.dao.dish.Dish;
import com.dualupa.canteen.dao.dish.Weight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.dualupa.canteen.dao.dish.Category.DRINK;
import static com.dualupa.canteen.dao.dish.Category.SOUP;
import static com.dualupa.canteen.dao.dish.Weight.Unit.GRAM;
import static com.dualupa.canteen.dao.dish.Weight.Unit.MILLILITER;
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

        Schedule fullWeek = Schedule.fullWeek();

        Canteen iuCanteen = new Canteen("Столовая ИУ", "ГЗ, 3 этаж", fullWeek);
        Canteen mainGZCanteen = new Canteen("ГЗ Главная", "ГЗ, слева", fullWeek);
        Canteen ulkCanteen = new Canteen("УЛК Главаня", "УЛК, 2 этаж", fullWeek);

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
    public List<Dish> getAllDishesSortedByPrice() {
        return this.dishes.stream()
                .sorted(Comparator.comparing(Dish::getPrice))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public List<Dish> getDishesForCanteenSortedByPrice(String canteenId) {
        return this.getAllDishesSortedByPrice().stream()
                .filter(dish -> dish.isAvailableAtCanteen(canteenId))
                .sorted(Comparator.comparing(Dish::getPrice))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public Collection<Canteen> getAllCanteens() {
        return this.dishes.stream()
                .map(Dish::getAvailableAt)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Canteen::getName, canteen -> canteen,
                        (canteen1, canteen2) -> canteen1))  // filter distinct elems
                .values();
    }

    @Nonnull
    @Override
    public Optional<Canteen> getCanteenById(@Nonnull String canteenId) {
        return this.getAllCanteens().stream()
                .filter(canteen -> canteen.getId().equalsIgnoreCase(canteenId))
                .findFirst();
    }
}
