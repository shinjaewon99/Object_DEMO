package _02_movie;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(final int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(final Screening screening) {
        return screening.isSequence(sequence);
    }
}
