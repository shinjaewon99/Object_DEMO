import _04_movie_data_system.DiscountCondition;
import _04_movie_data_system.DiscountConditionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class movie_data_system {
    private final LocalTime 할인_시작_시간 = LocalTime.of(9, 0);
    private final LocalTime 할인_종료_시간 = LocalTime.of(12, 0);
    private final DayOfWeek 할인_가능_요일 = DayOfWeek.MONDAY;
    private final int 할인_가능_순번 = 27;
    private int 할인_불가능_순번 = 할인_가능_순번 - 1;

    private final List<DayOfWeek> 모든_요일 = List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    );

    private final List<DayOfWeek> 할인_되지_않는_요일들 = 모든_요일
            .stream()
            .filter(요일 -> 요일 != 할인_가능_요일).toList();

    private DiscountCondition createPeriodDiscountCondition() {
        return new DiscountCondition(
                DiscountConditionType.PERIOD,
                27,
                할인_가능_요일,
                할인_시작_시간,
                할인_종료_시간
        );
    }

    private DiscountCondition createSequenceDiscountCondition() {
        return new DiscountCondition(
                DiscountConditionType.SEQUENCE,
                27,
                할인_가능_요일,
                할인_시작_시간,
                할인_종료_시간
        );
    }

    @Test
    @DisplayName("할인조건에 맞는 시간이 주어지고, 할인 조건에 맞는 요일이 주어지면")
    void valid_time_valid_day() {
        DiscountCondition condition = createPeriodDiscountCondition();

        List<LocalTime> 테스트_시간들 = List.of(
                할인_시작_시간,
                할인_시작_시간.plusMinutes(1),
                할인_종료_시간.minusMinutes(1),
                할인_종료_시간
        );

        final DayOfWeek 할인_조건에_맞는_요일 = 할인_가능_요일;

        for (LocalTime 시간 : 테스트_시간들) {
            assertTrue(condition.isDiscountable(할인_조건에_맞는_요일, 시간));
        }
    }

    @Test
    @DisplayName("할인조건에 맞는 시간이 주어지고, 할인 조건에 맞지않는 요일이 주어지면")
    void valid_time_Invalid_day() {
        DiscountCondition condition = createPeriodDiscountCondition();

        List<LocalTime> 테스트_시간들 = List.of(
                할인_시작_시간.minusMinutes(1),
                할인_시작_시간,
                할인_시작_시간.plusSeconds(1),
                할인_종료_시간.minusSeconds(1),
                할인_종료_시간,
                할인_종료_시간.plusMinutes(1)
        );

        final List<DayOfWeek> 요일들 = 할인_되지_않는_요일들;

        for (LocalTime 시간 : 테스트_시간들) {
            for (DayOfWeek 요일 : 요일들) {
                assertFalse(condition.isDiscountable(요일, 시간));
            }
        }
    }

    @Test
    @DisplayName("순번 예외")
    void valid_sequence() {
        DiscountCondition condition = createPeriodDiscountCondition();

        List<Integer> 순번들 = List.of(
                할인_가능_순번,
                할인_불가능_순번
        );

        for (int 순번 : 순번들) {
            assertThrows(IllegalArgumentException.class, () -> condition.isDiscountable(순번));
        }
    }

    @Test
    @DisplayName("할인 조건에 맞는 순번이 주어지면")
    void valid_discount_sequence() {
        DiscountCondition condition = createSequenceDiscountCondition();

        int sequence = condition.getSequence();

        Assertions.assertThat(condition.isDiscountable(sequence)).isTrue();
    }

    @Test
    @DisplayName("할인 조건에 맞지않는 순번이 주어지면")
    void valid_not_discount_sequence() {
        DiscountCondition condition = createSequenceDiscountCondition();

        int sequence = 할인_불가능_순번;

        Assertions.assertThat(condition.isDiscountable(sequence)).isFalse();
    }
}
