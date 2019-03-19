package com.dualupa.canteen.dao.canteen;

import lombok.*;

/**
 * @author avbelyaev
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for mongo
@ToString
public class Canteen {

    private static long count = 0;

    private String id;

    private String name;

    private String location;

    private Schedule schedule;

    private String imageUrl;

    @Builder
    public Canteen(String name, String location, Schedule schedule, String imageUrl) {
        this.name = name;
        this.location = location;
        this.schedule = schedule;
        this.imageUrl = imageUrl;
        this.id = String.valueOf(Canteen.count++);
    }
}
