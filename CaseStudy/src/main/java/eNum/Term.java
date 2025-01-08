package eNum;

public enum Term {
    THREE_MONTHS(0.03, 3),
    SIX_MONTHS(0.035, 6),
    ONE_YEAR(0.04, 12);

    private final double interestRate;
    private final int months;

    Term(double interestRate, int months) {
        this.interestRate = interestRate;
        this.months = months;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getMonths() {
        return months;
    }
}
