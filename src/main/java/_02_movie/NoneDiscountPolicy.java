package _02_movie;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(final Screening screening) {
        return Money.ZERO;
    }
}
