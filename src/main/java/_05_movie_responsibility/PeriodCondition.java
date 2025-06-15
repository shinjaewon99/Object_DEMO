package _05_movie_responsibility;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PeriodCondition(final DayOfWeek dayOfWeek, final LocalDateTime startTime, final LocalDateTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
                startTime.compareTo(ChronoLocalDateTime.from(screening.getWhenScreened().toLocalTime())) <= 0 &&
                endTime.compareTo(ChronoLocalDateTime.from(screening.getWhenScreened().toLocalTime())) >= 0;
    }
}
