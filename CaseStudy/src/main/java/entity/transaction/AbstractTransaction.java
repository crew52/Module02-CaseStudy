package entity.transaction;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractTransaction {
    private String transactionId;
    private Date transactionDate;
    private double amount;

    public AbstractTransaction(double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.transactionDate = new Date();
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Date: " + transactionDate + ", Amount: " + amount;
    }
}
