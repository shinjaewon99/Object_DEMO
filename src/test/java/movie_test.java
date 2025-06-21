import _02_movie.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Movie 테스트")
public class movie_test {
    private final Money FIX_MONEY = Money.wons(100);
    private final double FIX_DISCOUNT_PERCENT = 0.1;
    private final LocalDateTime 월요일 = LocalDate.of(2025, Month.JUNE, 21).atStartOfDay();
    private final LocalDateTime 화요일 = 월요일.plusDays(1);
    private final LocalDateTime 수요일 = 월요일.plusDays(2);
    private final LocalDateTime 목요일 = 월요일.plusDays(3);
    private final LocalDateTime 금요일 = 월요일.plusDays(4);
    private final LocalDateTime 토요일 = 월요일.plusDays(5);
    private final LocalDateTime 토요일_오후 = 토요일.withHour(13).withMinute(50);

    Movie settingMovie_범죄도시() {
        return new Movie(
                "범죄도시", Duration.ofMinutes(120),
                Money.wons(10000),
                setting_범죄도시_할인정책
        );
    }

    private final DiscountPolicy setting_범죄도시_할인정책 = new AmountDiscountPolicy(
            FIX_MONEY,
            new SequenceCondition(1),
            new SequenceCondition(10),
            new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
            new PeriodCondition(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
    );
}
