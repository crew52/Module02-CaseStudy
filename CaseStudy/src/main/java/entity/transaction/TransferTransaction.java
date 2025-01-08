package entity.transaction;

import java.util.Date;

public class TransferTransaction extends AbstractTransaction{
    private String targetAccountId;

    public TransferTransaction(String transactionId, Date transactionDate, double amount, String targetAccountId) {
        super(transactionId, transactionDate, amount);
        this.targetAccountId = targetAccountId;
    }

    @Override
    public String toString() {
        return "Transfer Transaction - " + super.toString() + ", Target Account: " + targetAccountId;
    }
}
