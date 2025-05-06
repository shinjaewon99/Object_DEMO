package _02_movie;

public class PercentDiscountPolicy extends DiscountPolicy{
    private double percent;

    public PercentDiscountPolicy(final double percent, final DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
