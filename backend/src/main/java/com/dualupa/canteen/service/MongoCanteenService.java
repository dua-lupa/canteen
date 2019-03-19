package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.dish.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * @author avbelyaev
 */
@Service
public class MongoCanteenService implements CanteenService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Nonnull
    @Override
    public List<Dish> getAllDishesSortedByPrice() {
        Aggregation aggregationPipeline = Aggregation.newAggregation(
                sort(Sort.Direction.ASC, "price")
        );
        return this.mongoTemplate.aggregate(aggregationPipeline, Dish.class, Dish.class)
                .getMappedResults();
    }

    @Nonnull
    @Override
    public List<Dish> getDishesForCanteenSortedByPrice(String canteenId) {
        return this.getAllDishesSortedByPrice().stream()
                .filter(dish -> dish.isAvailableAtCanteen(canteenId))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public Collection<Canteen> getAllCanteens() {
        return this.getAllDishesSortedByPrice().stream()
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
