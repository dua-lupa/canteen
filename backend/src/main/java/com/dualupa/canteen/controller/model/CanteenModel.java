package com.dualupa.canteen.controller.model;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.canteen.Schedule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author avbelyaev
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CanteenModel {

    private final String id;

    private final String name;

    private final String location;

    private final List<WorkingHoursModel> schedule;

    @JsonProperty("isOpen")
    public boolean isOpen() {
        return true;
    }

    public CanteenModel(Canteen canteen) {
        this.id = canteen.getId();
        this.name = canteen.getName();
        this.location = canteen.getLocation();
        this.schedule = canteen.getSchedule().getWorkingHours().stream()
                .map(WorkingHoursModel::new)
                .collect(Collectors.toList());
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class WorkingHoursModel {

        private String day;

        private LocalTime from;

        private LocalTime to;

        WorkingHoursModel(Schedule.WorkingHours workingHours) {
            this.day = workingHours.getDay().getName();
            this.from = workingHours.getFrom();
            this.to = workingHours.getTo();
        }
    }
}
