package com.dualupa.canteen.controller.model;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.canteen.Schedule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author avbelyaev
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CanteenModel {

    private final String id;

    private final String name;

    private final String location;

    private final Schedule schedule = null; // TODO implement

    @JsonProperty("isOpen")
    public boolean isOpen() {
        return true;
    }

    public CanteenModel(Canteen canteen) {
        this.id = canteen.getId();
        this.name = canteen.getName();
        this.location = canteen.getLocation();
    }
}
