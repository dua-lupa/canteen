package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.dish.Dish;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author avbelyaev
 */
public interface CanteenService {

    @Nonnull
    public List<Dish> getAllDishesSortedByPrice();

    @Nonnull
    public List<Dish> getDishesForCanteenSortedByPrice(String canteenId);

    @Nonnull
    public Collection<Canteen> getAllCanteens();

    @Nonnull
    public Optional<Canteen> getCanteenById(@Nonnull String canteenId);

}
