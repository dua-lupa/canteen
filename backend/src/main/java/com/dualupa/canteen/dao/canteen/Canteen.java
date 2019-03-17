package com.dualupa.canteen.dao.canteen;

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

    private Schedule schedule;

    public Canteen(String name, String location, Schedule schedule) {
        this.name = name;
        this.location = location;
        this.schedule = schedule;
        this.id = String.valueOf(Canteen.count++);
    }
}
