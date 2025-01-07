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

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void execute();
}
