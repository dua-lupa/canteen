package com.dualupa.canteen.dao.dish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author avbelyaev
 */
@AllArgsConstructor
@Data
public class Weight {
    private float weight;

    private Unit unit;

    @Getter
    public enum Unit {
        GRAM("г"),
        MILLILITER("мл");

        private final String name;

        Unit(@Nonnull String name) {
            this.name = name;
        }
    }
}
