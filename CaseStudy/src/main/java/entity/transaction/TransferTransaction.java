package entity.transaction;

import java.util.Date;

public class TransferTransaction extends AbstractTransaction{
    public TransferTransaction(String transactionId, Date transactionDate, double amount) {
        super(transactionId, transactionDate, amount);
    }

    @Override
    public void execute() {
        // TODO
    }

}
