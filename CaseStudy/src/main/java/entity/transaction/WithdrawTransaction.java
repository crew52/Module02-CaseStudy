package entity.transaction;

import java.util.Date;

public class WithdrawTransaction extends AbstractTransaction{
    public WithdrawTransaction(String transactionId, Date transactionDate, double amount) {
        super(transactionId, transactionDate, amount);
    }

    @Override
    public String toString() {
        return "Withdraw Transaction - " + super.toString();
    }
}
