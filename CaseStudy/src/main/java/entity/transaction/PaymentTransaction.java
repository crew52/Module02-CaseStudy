package entity.transaction;

import java.util.Date;

public class PaymentTransaction extends AbstractTransaction{
    public PaymentTransaction(String transactionId, Date transactionDate, double amount) {
        super(transactionId, transactionDate, amount);
    }

    @Override
    public void execute() {
        //TODO
    }
}
