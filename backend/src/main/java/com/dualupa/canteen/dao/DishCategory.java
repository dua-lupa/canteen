package com.dualupa.canteen.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

/**
 * @author avbelyaev
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DishCategory {

    SOUP("soup"),           // суп
    PORRIDGE("porridge"),   // каша
    SALAD("salad"),         // салат
    DRINK("drink");         // напиток

    private String category;

    DishCategory(@Nonnull String category) {
        this.category = category;
    }

    @JsonProperty("category")
    public String getCategory() {
        return this.category;
    }
}
