package _04_movie_data_system;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money getFee() {
        return fee;
    }

    public void setFee(final Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public void setDiscountConditions(final List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(final MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(final Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(final double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            // 할인 조건이 기간 조건이라면
            if (condition.getType() == DiscountConditionType.PERIOD) {
                if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                } else {
                    // 할인 조건이 순번 조건이라면
                    if (condition.isDiscountable(sequence)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(fee.times(discountPercent));
    }

    public Money calculatedNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee;
    }
}
