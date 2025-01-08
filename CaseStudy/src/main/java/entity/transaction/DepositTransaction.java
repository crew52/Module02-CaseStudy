package entity.transaction;

import java.util.Date;

public class DepositTransaction extends AbstractTransaction{
    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Deposit Transaction - " + super.toString();
    }
}
