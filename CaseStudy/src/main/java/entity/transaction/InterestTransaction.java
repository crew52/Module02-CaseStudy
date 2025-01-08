package entity.transaction;

public class InterestTransaction extends AbstractTransaction {
    public InterestTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Interest Transaction - " + super.toString();
    }
}
