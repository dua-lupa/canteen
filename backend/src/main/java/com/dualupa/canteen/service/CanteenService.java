package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.Canteen;
import com.dualupa.canteen.dao.Dish;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

/**
 * @author avbelyaev
 */
public interface CanteenService {

    @Nonnull
    public Collection<Dish> getAllDishes();
}
