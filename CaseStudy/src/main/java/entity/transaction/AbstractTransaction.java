package entity.transaction;

import java.util.Date;

public abstract class AbstractTransaction {
    private String transactionId;
    private Date transactionDate;
    private double amount;

    public AbstractTransaction(String transactionId, Date transactionDate, double amount) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
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
