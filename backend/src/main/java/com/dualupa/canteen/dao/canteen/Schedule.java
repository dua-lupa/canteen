package com.dualupa.canteen.dao.canteen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.dualupa.canteen.dao.canteen.Schedule.WorkingHours.*;
import static java.time.DayOfWeek.*;

/**
 * @author avbelyaev
 */
@Getter
public class Schedule {

    private List<WorkingHours> workingHours = new ArrayList<>();

    public static Schedule fullWeek() {
        Schedule week = new Schedule();
        week.workingHours.add(fullDay(MONDAY));
        week.workingHours.add(fullDay(TUESDAY));
        week.workingHours.add(fullDay(WEDNESDAY));
        week.workingHours.add(fullDay(THURSDAY));
        week.workingHours.add(partialDay(FRIDAY));
        week.workingHours.add(partialDay(SATURDAY));
        week.workingHours.add(closed(SUNDAY));
        return week;
    }

    public boolean isOpenNow() {
        return this.workingHours.stream()
                .anyMatch(WorkingHours::isOpenNow);
    }


    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class WorkingHours {

        private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
        private static final LocalTime CLOSING_TIME = LocalTime.of(17, 30);

        @Nonnull
        private DayOfWeek day;

        @Nonnull
        private LocalTime from;

        @Nonnull
        private LocalTime to;

        private boolean isDayOff;   // выходной

        static WorkingHours fullDay(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, OPENING_TIME, CLOSING_TIME, false);
        }

        static WorkingHours partialDay(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, OPENING_TIME, LocalTime.of(16, 0), false);
        }

        static WorkingHours closed(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, OPENING_TIME, CLOSING_TIME, true);
        }

        boolean isOpenNow() {
            LocalDateTime now = LocalDateTime.now();
            return !this.isDayOff
                    && now.getDayOfWeek().equals(day)
                    && this.from.isBefore(now.toLocalTime())
                    && this.to.isAfter(now.toLocalTime());
        }
    }
}
