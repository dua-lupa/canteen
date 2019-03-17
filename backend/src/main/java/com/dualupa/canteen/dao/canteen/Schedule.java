package com.dualupa.canteen.dao.canteen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.dualupa.canteen.dao.canteen.Schedule.WorkingHours.DayOfWeek.*;

/**
 * @author avbelyaev
 */
@Getter
public class Schedule {

    private List<WorkingHours> workingHours = new ArrayList<>();

    public static Schedule fullWeek() {
        Schedule week = new Schedule();
        week.workingHours.add(WorkingHours.fullDay(MONDAY));
        week.workingHours.add(WorkingHours.fullDay(TUESDAY));
        week.workingHours.add(WorkingHours.fullDay(WEDNESDAY));
        week.workingHours.add(WorkingHours.fullDay(THURSDAY));
        week.workingHours.add(WorkingHours.partialDay(FRIDAY));
        week.workingHours.add(WorkingHours.partialDay(SATURDAY));
        week.workingHours.add(WorkingHours.closed(SUNDAY));
        return week;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class WorkingHours {

        private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
        private static final LocalTime CLOSING_TIME = LocalTime.of(17, 30);

        @Nonnull
        private DayOfWeek day;

        @Nullable
        private LocalTime from;

        @Nullable
        private LocalTime to;

        static WorkingHours fullDay(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, OPENING_TIME, CLOSING_TIME);
        }

        static WorkingHours partialDay(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, OPENING_TIME, LocalTime.of(16, 0));
        }

        static WorkingHours closed(@Nonnull DayOfWeek day) {
            return new WorkingHours(day, null, null);
        }

        @Getter
        public enum DayOfWeek {
            MONDAY("пн"),
            TUESDAY("вт"),
            WEDNESDAY("ср"),
            THURSDAY("чт"),
            FRIDAY("пт"),
            SATURDAY("сб"),
            SUNDAY("вс");

            private String name;

            DayOfWeek(@Nonnull String name) {
                this.name = name;
            }
        }
    }
}
