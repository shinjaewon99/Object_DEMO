import _02_movie.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

@DisplayName("Movie 테스트")
public class movie_test {
    private final Money FIX_MONEY = Money.wons(100);
    private final double FIX_DISCOUNT_PERCENT = 0.1;
    private final LocalDateTime 월요일 = LocalDate.of(2025, Month.JUNE, 23).atStartOfDay();
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

    class FIX_MOVIE_범죄도시 {
        Movie 범죄도시_생성() {
            return settingMovie_범죄도시();
        }
    }

    @Test
    @DisplayName("범죄도시_고정할인")
    void 범죄도시_고정할인() {
        FIX_MOVIE_범죄도시 범죄도시_생성 = new FIX_MOVIE_범죄도시();
        Movie 범죄도시 = 범죄도시_생성.범죄도시_생성();

        final List<LocalDateTime> 할인_조건에_맞는_상영_시작_시간들 = List.of(
                // 월요일 시간 boundary 설정
                월요일.withHour(10).withMinute(0),
                월요일.withHour(11).withMinute(59),
                월요일.withHour(10).withMinute(1),
                월요일.withHour(11).withMinute(58),

                // 목요일 시간 boundary 설정
                목요일.withHour(10).withMinute(0),
                목요일.withHour(20).withMinute(59),
                목요일.withHour(10).withMinute(1),
                목요일.withHour(11).withMinute(58)
        );

        List<Screening> screenings = 할인_조건에_맞는_상영_시작_시간들.stream()
                .map(상영시간 -> new Screening(범죄도시, 1, 상영시간)).toList();

        Money 기본요금 = 범죄도시.getFee();

        for (Screening screening : screenings) {
            Money 계산된금액 = 범죄도시.calculateMovieFee(screening);
            Assertions.assertThat(계산된금액).isEqualTo(기본요금.minus(FIX_MONEY));
        }
    }

    @Test
    @DisplayName("범죄도시_고정할인")
    void 범죄도시_할인_제외() {
        FIX_MOVIE_범죄도시 범죄도시_생성 = new FIX_MOVIE_범죄도시();
        Movie 범죄도시 = 범죄도시_생성.범죄도시_생성();

        final List<LocalDateTime> 할인_조건에_맞지않는_상영_시작_시간들 = List.of(
                // 월요일 시간 boundary 설정
                월요일.withHour(9).withMinute(59),
                월요일.withHour(12).withMinute(0),

                // 목요일 시간 boundary 설정
                목요일.withHour(9).withMinute(59),
                목요일.withHour(20).withMinute(59),

                // 그 외의 요일
                토요일.withHour(10).withMinute(0)
        );

        List<Screening> screenings = 할인_조건에_맞지않는_상영_시작_시간들.stream()
                .map(상영시간 -> new Screening(범죄도시, -1, 상영시간)).toList();

        Money 기본요금 = 범죄도시.getFee();

        for (Screening screening : screenings) {
            Money 계산된금액 = 범죄도시.calculateMovieFee(screening);
            Assertions.assertThat(계산된금액).isEqualTo(기본요금);
        }
    }
}