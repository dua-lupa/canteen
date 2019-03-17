package com.dualupa.canteen.dao.dish;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("unit")
    public String unit() {
        return this.unit.getName();
    }

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
