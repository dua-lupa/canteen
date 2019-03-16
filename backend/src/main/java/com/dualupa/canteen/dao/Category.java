package com.dualupa.canteen.dao;

import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author avbelyaev
 */
@Getter
public enum Category {

    SOUP("суп"),
    PORRIDGE("каша"),
    SALAD("салат"),
    DRINK("напиток");

    private String name;

    Category(@Nonnull String name) {
        this.name = name;
    }

}
