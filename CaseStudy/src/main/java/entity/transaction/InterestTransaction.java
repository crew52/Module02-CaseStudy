package entity.transaction;

import java.util.Date;

public class InterestTransaction extends AbstractTransaction {
    public InterestTransaction(String transactionId, Date transactionDate, double amount) {
        super(transactionId, transactionDate, amount);
    }

    @Override
    public String toString() {
        return "Interest Transaction - " + super.toString();
    }
}
