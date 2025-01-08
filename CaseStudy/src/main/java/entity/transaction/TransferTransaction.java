package entity.transaction;

public class TransferTransaction extends AbstractTransaction{
    private String targetAccountId;

    public TransferTransaction(double amount, String targetAccountId) {
        super(amount);
        this.targetAccountId = targetAccountId;
    }

    @Override
    public String toString() {
        return "Transfer Transaction - " + super.toString() + ", Target Account: " + targetAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }
}
