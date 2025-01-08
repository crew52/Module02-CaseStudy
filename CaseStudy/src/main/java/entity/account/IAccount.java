package entity.account;

public interface IAccount {
    void deposit(double amount, boolean isTransfer);
    void withdraw(double amount, boolean isTransfer);
}
