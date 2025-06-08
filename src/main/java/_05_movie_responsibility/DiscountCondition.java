package _05_movie_responsibility;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
