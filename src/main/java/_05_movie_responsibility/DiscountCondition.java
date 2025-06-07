package _05_movie_responsibility;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DiscountCondition {
    private DiscountCondition type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public boolean isSatisfiedBy(Screening screening) {

    }
}
