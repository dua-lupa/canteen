package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.Dish;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * @author avbelyaev
 */
public interface DishService {

    @Nonnull
    public Collection<Dish> getAllDishes();
}
