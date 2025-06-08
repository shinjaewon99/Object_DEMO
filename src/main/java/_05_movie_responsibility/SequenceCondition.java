package _05_movie_responsibility;

public class SequenceCondition implements DiscountCondition {

    private int sequence;

    public SequenceCondition(final int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
