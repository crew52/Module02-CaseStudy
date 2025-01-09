package service.account;

import entity.account.SavingsAccount;

public interface SavingsAccountService {
    void applyInterest(SavingsAccount account);
    boolean canWithdraw(SavingsAccount account);
    void withdrawEarly(SavingsAccount account, double amount);
}
