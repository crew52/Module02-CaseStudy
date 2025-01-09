package entity.transaction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
