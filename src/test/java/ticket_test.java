
import _01_ticet.Audience;
import _01_ticet.Bag;
import _01_ticet.Invitation;
import _01_ticet.Ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ticket 테스트")
class ticket_test {
    final Long givenTicketFee = 100L;

    Ticket givenTicket;
    @BeforeEach
    void setUp() {
        givenTicket = new Ticket(givenTicketFee);
    }

    @Test
    @DisplayName("초대장이 있는 관람객이 buy() 메서드 호출 시, 티켓을 받고 0원을 지불한다")
    void buy_with_invitation_returns_0_and_adds_ticket_to_bag() {
        Invitation 초대장 = new Invitation();
        Bag 가방 = new Bag(초대장, 100_000L);
        Audience 관람객 = new Audience(가방);

        Long 지불금액 = 관람객.buy(givenTicket);

        assertThat(가방.hasTicket())
                .as("가방에는 티켓이 들어있다")
                .isTrue();

        assertThat(지불금액)
                .as("관람객은 티켓 비용으로 0원을 지불했다")
                .isEqualTo(0L);
    }

    @Test
    @DisplayName("초대장이 없는 관람객이 buy() 호출 시, 티켓을 받고 티켓 가격만큼 지불한다")
    void buy_without_invitation_returns_ticket_price_and_adds_ticket_to_bag() {
        Bag 가방 = new Bag(100_000L); // 초대장 없음
        Audience 관람객 = new Audience(가방);

        Long 지불금액 = 관람객.buy(givenTicket);

        assertThat(가방.hasTicket())
                .as("가방에는 티켓이 들어있다")
                .isTrue();

        assertThat(지불금액)
                .as("관람객이 지불한 돈은 티켓 값만큼이다")
                .isEqualTo(givenTicketFee);
    }
}