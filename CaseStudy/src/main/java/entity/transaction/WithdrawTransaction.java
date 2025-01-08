package entity.transaction;

public class WithdrawTransaction extends AbstractTransaction{
    public WithdrawTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Withdraw Transaction - " + super.toString();
    }
}
