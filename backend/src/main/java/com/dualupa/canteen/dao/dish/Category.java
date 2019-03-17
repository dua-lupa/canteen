package com.dualupa.canteen.dao.dish;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author avbelyaev
 */
@AllArgsConstructor
@Getter
public enum Category {

    SOUP("суп"),
    PORRIDGE("каша"),
    SALAD("салат"),
    DRINK("напиток");

    private String name;

}
