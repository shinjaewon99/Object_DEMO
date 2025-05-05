package _01_ticet;

public class Theater {
    private TicketSeller ticketSeller;

    /**
     * 입장시 TicketSeller를 거쳐야 함
     *
     * @param ticketSeller
     */
    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
