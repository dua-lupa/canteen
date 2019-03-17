package com.dualupa.canteen.dao.dish;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author avbelyaev
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for mongo
public class Weight {

    private float weight;

    private Unit unit;

    @AllArgsConstructor
    @Getter
    public enum Unit {
        GRAM("г"),
        MILLILITER("мл");

        private final String name;
    }
}
