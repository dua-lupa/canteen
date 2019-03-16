package com.dualupa.canteen.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author avbelyaev
 */
@Data
@EqualsAndHashCode
@ToString
public class Canteen {

    private static long count = 0;

    private String id;

    private String name;

    private String location;

    public Canteen(String name, String location) {
        this.name = name;
        this.location = location;
        this.id = String.valueOf(Canteen.count++);
    }
}
