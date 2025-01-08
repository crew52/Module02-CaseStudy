package entity.transaction;

import java.util.Date;

public class DepositTransaction extends AbstractTransaction{
    public DepositTransaction(String transactionId, Date transactionDate, double amount) {
        super(transactionId, transactionDate, amount);
    }

    @Override
    public String toString() {
        return "Deposit Transaction - " + super.toString();
    }
}
