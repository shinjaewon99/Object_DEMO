package _02_movie;

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

    @Override
    public boolean isSatisfiedBy(final Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(ChronoLocalDateTime.from(screening.getStartTime().toLocalTime())) <= 0 &&
                endTime.compareTo(ChronoLocalDateTime.from(screening.getStartTime().toLocalTime())) >= 0;
    }
}
