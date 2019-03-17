package com.dualupa.canteen.controller.model;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.canteen.Schedule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.annotation.Nonnull;
import java.time.DayOfWeek;
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

    private final boolean isOpenNow;        // столовая открыта в данный момент

    public CanteenModel(Canteen canteen) {
        this.id = canteen.getId();
        this.name = canteen.getName();
        this.location = canteen.getLocation();
        this.schedule = canteen.getSchedule().getWorkingHours().stream()
                .map(WorkingHoursModel::new)
                .collect(Collectors.toList());
        this.isOpenNow = canteen.getSchedule().isOpenNow();
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class WorkingHoursModel {

        private final String day;

        private LocalTime from = null;

        private LocalTime to = null;

        private final boolean isDayOff;     // выходной

        WorkingHoursModel(Schedule.WorkingHours workingHours) {
            this.day = dayOfWeek(workingHours.getDay());
            this.isDayOff = workingHours.isDayOff();

            // для выходного дня расписание не возвращаем
            if (!this.isDayOff) {
                this.from = workingHours.getFrom();
                this.to = workingHours.getTo();
            }
        }

        private static String dayOfWeek(@Nonnull DayOfWeek dayOfWeek) {
            switch (dayOfWeek) {
                case MONDAY:
                    return "пн";
                case TUESDAY:
                    return "вт";
                case WEDNESDAY:
                    return "ср";
                case THURSDAY:
                    return "чт";
                case FRIDAY:
                    return "пт";
                case SATURDAY:
                    return "сб";
                case SUNDAY:
                    return "вс";
                default:
                    return "wtf";
            }
        }
    }
}
